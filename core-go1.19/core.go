package gfaas_core_go

import (
	"errors"
	"fmt"
	"net/http"
	"os"
)

type XFunction interface {
	Call(w http.ResponseWriter, r *http.Request)
}

func RunFunction(function XFunction) {
	fmt.Printf("Function is listening on port 8080\n")
	// Health endpoint for Nuclio
	http.HandleFunc("/_/health", health)
	http.HandleFunc("/_/ready", ready)
	// Health endpoint for Nuclio
	http.HandleFunc("/__internal/health", health)
	http.HandleFunc("/", function.Call)

	// Start health server to fillfil Nuclio health checks
	http.HandleFunc("/live", health)
	go func() {
		err := http.ListenAndServe(":8082", nil)
		if err != nil {
			fmt.Printf("Could not start health server: %s\n", err)
		}
	}()

	err := http.ListenAndServe(":8080", nil)
	fmt.Printf("Function server\n")

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
func health(w http.ResponseWriter, _ *http.Request) {
	_, err := fmt.Fprintf(w, "Ok")
	if err != nil {
		return
	}
}

func ready(w http.ResponseWriter, _ *http.Request) {
	_, err := fmt.Fprintf(w, "Ok")
	if err != nil {
		return
	}
}
