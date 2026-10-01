println "===== Lists ====="

def sourceDirs = [
    'app/src',
    'app/include'
]

println sourceDirs[0]

sourceDirs.each { dir ->
    println "Checking ${dir}..."
}

sourceDirs.each {
    println "Checking ${it} using it..."
}

println "===== Maps ====="

def config = [
    buildDir: 'build',
    buildType: 'Debug',
    buildTesting: true
]

println "----- Access through the dot -----"
println "Dir: ${config.buildDir}"
println "Type: ${config.buildType}"
println "Testing: ${config.buildTesting}"

println "----- Access by key name -----"
println "Dir: ${config['buildDir']}"
println "Type: ${config['buildType']}"
println "Testing: ${config['buildTesting']}"

println "----- get with default -----"
println "Dir: ${config.get('buildDir', 'default_build_dir')}"
println "Non-existent key: ${config.get('nonexistent', 'unknown_default')}"

println "===== Closures ====="