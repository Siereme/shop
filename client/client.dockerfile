FROM node:alpine3.14
RUN mkdir -p /app
WORKDIR /app
ARG PACKAGE_JSON_FILE=package*.json
COPY ${PACKAGE_JSON_FILE} ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE $PORT
ENV PROXY_API=$PROXY_API