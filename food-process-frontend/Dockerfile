# Use the official Node.js image as the base image
FROM node:22.1.0-alpine AS build

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the package.json and package-lock.json files to the container
COPY package*.json /usr/src/app/

# Install Angular CLI
RUN npm install -g @angular/cli

# Install dependencies
RUN npm install

# Copy the rest of the application
COPY . .

# Build the Angular application
RUN ng build --prod

# Use Nginx as the base image
FROM nginx:alpine

# Remove the default Nginx configuration file
RUN rm /etc/nginx/conf.d/default.conf

# Copy the built Angular application to Nginx's html directory
COPY --from=build /usr/src/app/dist/food-process-frontend /usr/share/nginx/html

# Expose port 80
EXPOSE 80
