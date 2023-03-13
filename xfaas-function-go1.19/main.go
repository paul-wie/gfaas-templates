package main

import (
	"errors"
	"fmt"
	"net/http"
	"os"
)

func main() {
	fmt.Printf("XFaaS Function is listening on port 8080\n")
	http.HandleFunc("/", Function)
	err := http.ListenAndServe(":8080", nil)

	if errors.Is(err, http.ErrServerClosed) {
		fmt.Printf("Server was closed.")
	} else if err != nil {
		fmt.Printf("Error starting server: %s\n", err)
		os.Exit(1)
	}

}
