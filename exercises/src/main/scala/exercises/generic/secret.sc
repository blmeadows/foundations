import exercises.generic.GenericFunctionExercises._

secret

secret.map(_.map(_.toChar).reverse).swap

secret.map(bytes => new String(bytes.toArray).reverse).swap
