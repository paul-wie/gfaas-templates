package main

import (
	"fmt"
	"net/http"
)

func Function(w http.ResponseWriter, r *http.Request) {
	_, err := fmt.Fprintf(w, "Hello from XFaaS Function Go 1.19")
	if err != nil {
		return
	}
}
