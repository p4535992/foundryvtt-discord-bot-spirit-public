from __future__ import annotations

import re
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
OPENAPI_DIR = ROOT / "src" / "main" / "resources" / "META-INF" / "openapi" / "relay" / "v13_1"
SOURCE = OPENAPI_DIR / "openapi-v3-foundry-rest-api-relay.yaml"
CORE_TARGET = OPENAPI_DIR / "openapi-v3-foundry-rest-api-relay-core.yaml"
DND5E_TARGET = OPENAPI_DIR / "openapi-v3-foundry-rest-api-relay-dnd5e.yaml"

HTTP_METHODS = {"get", "post", "put", "delete", "patch", "options", "head", "trace"}


def split_top_level_sections(lines: list[str]) -> tuple[list[str], list[str], list[str]]:
    tags_idx = None
    paths_idx = None

    for index, line in enumerate(lines):
        if line == "tags:":
            tags_idx = index
        elif line == "paths:":
            paths_idx = index
            break

    if tags_idx is None or paths_idx is None or tags_idx >= paths_idx:
        raise ValueError("Unable to locate top-level 'tags' and 'paths' sections.")

    return lines[:tags_idx], lines[tags_idx:paths_idx], lines[paths_idx + 1 :]


def parse_tag_blocks(tag_lines: list[str]) -> list[tuple[str, list[str]]]:
    if not tag_lines or tag_lines[0] != "tags:":
        raise ValueError("Invalid tags section.")

    blocks: list[tuple[str, list[str]]] = []
    current_name: str | None = None
    current_block: list[str] = []

    for line in tag_lines[1:]:
        if line.startswith("  - "):
            if current_name is not None:
                blocks.append((current_name, current_block))
            current_block = [line]
            match = re.match(r"  - name:\s*(.+)$", line)
            current_name = match.group(1).strip() if match else None
        elif current_name is not None:
            current_block.append(line)

    if current_name is not None:
        blocks.append((current_name, current_block))

    return blocks


def parse_path_blocks(path_lines: list[str]) -> list[tuple[str, list[str]]]:
    blocks: list[tuple[str, list[str]]] = []
    current_path: str | None = None
    current_block: list[str] = []

    for line in path_lines:
        if line and not line.startswith(" "):
            if current_path is not None:
                blocks.append((current_path, current_block))
            break

        if line.startswith("  /"):
            if current_path is not None:
                blocks.append((current_path, current_block))
            current_path = line.strip()[:-1]
            current_block = [line]
        elif current_path is not None:
            current_block.append(line)

    if current_path is not None and (not blocks or blocks[-1][0] != current_path):
        blocks.append((current_path, current_block))

    return blocks


def extract_tags(path_block: list[str]) -> set[str]:
    tags: set[str] = set()
    in_operation = False
    in_tags = False

    for line in path_block[1:]:
        stripped = line.strip()

        if re.match(r"^    [a-z]+:\s*$", line):
            method = stripped[:-1]
            in_operation = method in HTTP_METHODS
            in_tags = False
            continue

        if not in_operation:
            continue

        if line == "      tags:":
            in_tags = True
            continue

        if in_tags and line.startswith("        - "):
            tags.add(line.strip()[2:].strip())
            continue

        if in_tags and not line.startswith("        "):
            in_tags = False

    return tags


def build_spec(
    header_lines: list[str],
    tag_blocks: list[tuple[str, list[str]]],
    path_blocks: list[tuple[str, list[str]]],
    extra_tag_names: list[str],
) -> str:
    spec_lines: list[str] = []
    spec_lines.extend(header_lines)
    spec_lines.append("tags:")

    used_tags = set()
    for _, block in path_blocks:
        used_tags.update(extract_tags(block))

    for tag_name, tag_block in tag_blocks:
        if tag_name in used_tags:
            spec_lines.extend(tag_block)

    for extra_tag in extra_tag_names:
        if extra_tag in used_tags:
            spec_lines.append(f"  - name: {extra_tag}")

    spec_lines.append("paths:")
    for _, block in path_blocks:
        spec_lines.extend(block)

    return "\n".join(spec_lines) + "\n"


def main() -> None:
    lines = SOURCE.read_text(encoding="utf-8").splitlines()
    header_lines, tag_lines, path_lines = split_top_level_sections(lines)
    tag_blocks = parse_tag_blocks(tag_lines)
    all_path_blocks = parse_path_blocks(path_lines)

    core_path_blocks: list[tuple[str, list[str]]] = []
    dnd5e_path_blocks: list[tuple[str, list[str]]] = []

    for path, block in all_path_blocks:
        tags = extract_tags(block)
        if path.startswith("/dnd5e/") or "DnD5e" in tags:
            dnd5e_path_blocks.append((path, block))
        else:
            core_path_blocks.append((path, block))

    CORE_TARGET.write_text(
        build_spec(header_lines, tag_blocks, core_path_blocks, extra_tag_names=["default"]),
        encoding="utf-8",
    )
    DND5E_TARGET.write_text(
        build_spec(header_lines, tag_blocks, dnd5e_path_blocks, extra_tag_names=[]),
        encoding="utf-8",
    )

    print(f"core paths: {len(core_path_blocks)} -> {CORE_TARGET}")
    print(f"dnd5e paths: {len(dnd5e_path_blocks)} -> {DND5E_TARGET}")


if __name__ == "__main__":
    main()
