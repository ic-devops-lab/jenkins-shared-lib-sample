def call(String message, Closure body) {
  echo "START: ${message}"

  body()

  echo "END: ${message}"
}