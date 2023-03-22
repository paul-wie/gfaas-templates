package xfaas_core_go

import (
	"fmt"
	"net/http"
)

// MyFunction implements the XFunction interface and can be passed to RunFunction
type MyFunction struct {
	XFunction
}

func (f MyFunction) Call(w http.ResponseWriter, r *http.Request) {
	_, err := fmt.Fprintf(w, "Hello from XFaaS Function Go 1.19")
	if err != nil {
		return
	}
}

func main() {
	RunFunction(MyFunction{})
}
