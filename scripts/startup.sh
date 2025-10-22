#!/bin/bash
cd "$(dirname "$0")/.."
# Build Docker image
docker build -t psp .

# Stop old container if exists
if [ "$(docker ps -aq -f name=psp-container)" ]; then
  docker rm -f psp-container
fi

# Run container
docker run -d --name psp-container -p 8080:8080 psp

echo "PSP service running at http://localhost:8080"