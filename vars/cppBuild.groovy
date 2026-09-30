def call(Map config = [:]) {
    /// Packages a standard configure-and-build C++ projectworkflow into a single reusable step that
    /// can be called from a Jenkins pipeline with different build settings.

    def buildDir = config.get('buildDir', 'build')
    def buildType = config.get('buildType', 'Debug')
    def buildTesting = config.get('buildTesting', true)

    sh """
    set -euo pipefail

    cmake \
        -S . \
        -B '${buildDir}' \
        -DCMAKE_BUILD_TYPE='${buildType}' \
        -DBUILD_TESTING=${buildTesting ? 'ON' : 'OFF'}

        cmake --build '${buildDir}' --parallel
    """
}