/**
 * Checks C++ source files for clang-format violations.
 *
 * @param config A map containing optional settings:
 *   - sourceDirs: directories to scan (default ['src', 'include'])
 *   - extensions: file extensions to check (default ['cpp', 'hpp'])
 */
def call(Map config = [:]) {
  echo "Shared Library: clangFormatCheck() starts working..."

  def sourceDirs = config.get(
    'sourceDirs',
    ['src', 'include']
  )
  def extensions = config.get(
    'extensions',
    ['cpp', 'hpp']
  )

  def sources = sourceDirs.join(' ')

  def nameFilters = extensions
    .collect { ext -> "-name '*.${ext}'" }
    .join(' -o ')

  echo "Checking formatting in: ${sources}"
  echo "File extensions: ${extensions.join(', ')}"

  sh """
    find ${sources}  \
      -type f \
      \\( ${nameFilters} \\) \
      -print0 |
    xargs -0 clang-format --dry-run --Werror
  """

  echo "Shared Library: clangFormatCheck() finished successfully."
}