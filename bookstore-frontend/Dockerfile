FROM node:lts AS build

WORKDIR /app

COPY . .

RUN npm install && npm run build

CMD ["npm", "start"]