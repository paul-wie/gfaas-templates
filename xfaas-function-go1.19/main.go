package main

import (
	"errors"
	"fmt"
	"net/http"
	"os"
)

func main() {
	fmt.Printf("XFaaS Function is listening on port 8080\n")
	// Health endpoint for Nuclio
	http.HandleFunc("/_/health", Health)
	http.HandleFunc("/_/ready", Ready)
	// Health endpoint for Nuclio
	http.HandleFunc("/__internal/health", Health)
	http.HandleFunc("/", Function)
	err := http.ListenAndServe(":8080", nil)

	if errors.Is(err, http.ErrServerClosed) {
		fmt.Printf("Server was closed.")
	} else if err != nil {
		fmt.Printf("Error starting server: %s\n", err)
		os.Exit(1)
	}

}

/*
Health and Ready endpoint for OpenFaaS
*/
func Health(w http.ResponseWriter, _ *http.Request) {
	_, err := fmt.Fprintf(w, "Ok")
	if err != nil {
		return
	}
}

func Ready(w http.ResponseWriter, _ *http.Request) {
	_, err := fmt.Fprintf(w, "Ok")
	if err != nil {
		return
	}
}
