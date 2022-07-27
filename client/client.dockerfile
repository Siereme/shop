FROM node:alpine3.14
RUN mkdir -p /app
WORKDIR /app
COPY client/package*.json ./
RUN npm install
COPY client/ .
RUN npm run build
EXPOSE $PORT
ENV PROXY_API=$PROXY_API