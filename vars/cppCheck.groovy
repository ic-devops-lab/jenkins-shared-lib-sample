/**
 * Runs cppcheck over the configured source directories and writes findings to a report file.
 *
 * @param config A map containing optional settings:
 *   - sourceDirs: directories to scan (default ['src', 'include'])
 *   - failOnIssue: fail the build if cppcheck reports issues (default false)
 *   - reportFile: output file for the cppcheck report (default 'cppcheck-report.txt')
 */
def call(Map config = [:]) {
  echo "Shared Library: cppCheck() starts working..."

  def sourceDirs = config.get(
    'sourceDirs',
    ['src', 'include']
  )
  def failOnIssue = config.get(
    'failOnIssue',
    false
  )
  def reportFile = config.get(
    'reportFile',
    'cppcheck-report.txt'
  )

  def sources = sourceDirs.join(' ')

  def cppCheckCommand = "cppcheck --enable=all --inconclusive --quiet"
  cppCheckCommand += " ${sources} 2> ${reportFile}"

  echo "Running cppcheck against: ${sources}"

  def exitCode = sh(
    script: cppCheckCommand,
    returnStatus: true
  )

  if (exitCode != 0) {

    if (failOnIssue) {
      error("cppcheck failed with exit code ${exitCode}. See ${reportFile}.")
    }

    echo "cppcheck returned exit code ${exitCode}, but failOnIssue=false. Continuing."
  }

  echo "Shared Library: cppCheck() finished successfully."
}