FROM node:alpine3.14
RUN mkdir -p /app
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE $PORT
ENV PROXY_API=$PROXY_API