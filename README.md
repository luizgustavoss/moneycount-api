[![Run Status](https://api.shippable.com/projects/5a861b0085ee94070034dc8f/badge?branch=master)](https://app.shippable.com/github/luizgustavoss/moneycount-api)

[![Coverage Badge](https://api.shippable.com/projects/5a861b0085ee94070034dc8f/coverageBadge?branch=master)](https://app.shippable.com/github/luizgustavoss/moneycount-api)

# MoneyCount API


A simple API that can be used to define the amount of notes and coins (cash) you need for one or more monetary values.



## Important Concepts

Although this is a simple and straigth API some explanations are important.

This API is useful for those who need to deal with lots of cash for paying bills. For example, suppose somebody or some company needs to pay its employees some amount of money in cash, with values for each employee being different, for some reason. In most cases it could be paied direcly in bank accounts or even payment in check, but if for some reason it needs to be in cash, withdraw the money in the bank in the exact quantity of notes and coins to sum each payment can be a challenge. 

Money Count helps in this: you can use the API to calculate the amount of notes and coins for a monetary event (the whole employees payment), and for each entry.



## Ubiquitous Language

To better undestant the functionality, bellow there are some common terms that belong to the ubiquitous language of the context of the project:

**Currency** - represents a currency used in one or more countries, for example Dollar (USD), Real (BRL). At the moment the API only consider these two currencies, but more currencies will be available soon.

**Currency Filter** - if for some reason you can't rely on one or more notes or coins for your payments, whathever reason, you have a chance to say witch notes and coins will be considered. A *Currency Filter* is specific for a *Currency*.

**Event Entry** - a single payment you have to do.

**Event** - represents an event in witch you have a group of payments (*Event Entry*) to do. You decide what an *Event* means for you, it could be all the payments to do in one day, or the payments of a week. The important thing is that you can group the values to calculate the amount of notes and coins to be used.



## Architectural Decision Records

[Here](./doc/adr/index.md) you can view the ADR index of the project.



## Cloning and Running

To run the project you must have **_Maven 3.x_** and **_Java 8_** installed and configured. You can clone and run the project through the commands bellow:

```
$ git clone https://github.com/luizgustavoss/moneycount-api.git && cd moneycount-api

$ mvn clean install spring-boot:run
```

Once you have run the project, you can access http://localhost:8080/swagger-ui.html to see the API documentation.



## Try it out!


### Live

You can test the API live through a [Swagger Documentation](https://moneycount-api.herokuapp.com/swagger-ui.html) (Don't worry if it takes some time to load the first time)


### On Docker!

If you prefer, you can run directly a docker container from an image of the project:

```
$ docker run -d -p 8080:8080 luizgustavoss/moneycount-api:latest
```


## Contribute

Although this is just an API with educational and learning purposes, feel free to fork it and improve it as you need.
Future improvements include support to other currencies, NoSQL persistence support for currencies, React UI, Vue UI, Angular UI, etc, all with learning purposes.
