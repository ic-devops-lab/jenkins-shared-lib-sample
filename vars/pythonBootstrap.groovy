def call(Map config = [:]) {
  /*
   * Creates a Python virtual environment and installs the project dependencies.
   *
   * @param config Optional configuration map with:
   *   - requirements: path to requirements file (default: "requirements.txt")
   *   - venvDir: virtual environment directory (default: ".venv")
   *   - pythonCmd: Python executable to use (default: "python3")
   */
  echo "Shared Library: pythonBootstrap() starts working..."

  def requirements = config.get('requirements', "requirements.txt")
  def venvDir = config.get('venvDir', '.venv')
  def pythonCmd = config.get('pythonCmd', 'python3')

  if (!fileExists(requirements)) {
    error("pythonBootstrap: requirements file not found: ${requirements}")
  }

  sh """
    set -euo pipefail

    ${pythonCmd} -m venv '${venvDir}'
    '${venvDir}/bin/python' -m pip install --upgrade pip
    '${venvDir}/bin/python' -m pip install -r '${requirements}'
  """

  echo "Shared Library: pythonBootstrap() finished successfully."
}