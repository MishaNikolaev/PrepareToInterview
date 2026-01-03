package functions.scope_functions

inline fun <T,R> T.run(operation : T.()->R) : R{
	return operation()
}