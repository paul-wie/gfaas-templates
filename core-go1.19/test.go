package gfaas_core_go

import (
	"fmt"
	"net/http"
)

// MyFunction implements the XFunction interface and can be passed to RunFunction
type MyFunction struct {
	XFunction
}

func (f MyFunction) Call(w http.ResponseWriter, r *http.Request) {
	_, err := fmt.Fprintf(w, "Hello World!")
	if err != nil {
		return
	}
}

//func main() {
//	RunFunction(MyFunction{})
//}
