package main

import (
	"fmt"
	xfaas_core_go "github.com/paul-wie/xfaas-templates/xfaas-core-go1.19"
	"net/http"
)

// MyFunction implements the XFunction interface and can be passed to RunFunction
type MyFunction struct {
	xfaas_core_go.XFunction
}

func (f MyFunction) Call(w http.ResponseWriter, r *http.Request) {
	// Place here your custom code
	_, err := fmt.Fprintf(w, "Hello from XFaaS Function Go 1.19")
	if err != nil {
		return
	}
}

func RunFunction() {
	xfaas_core_go.RunFunction(MyFunction{})
}

func main() {
	RunFunction()
}
