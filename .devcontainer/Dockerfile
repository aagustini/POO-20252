# Imagem base com Java 21 (OpenJDK) e Maven pré-instalados
FROM mcr.microsoft.com/devcontainers/java:1-21-bullseye

# Atualização de pacotes e instalação do Graphviz em um único layer
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y graphviz && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

