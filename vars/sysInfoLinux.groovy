def call() {
  sh """
    echo "=== System Information ==="
    uname -a
    cat /etc/os-release
    echo "=== End of System Information ==="
    echo "Current user: \$(whoami)"
  """
}