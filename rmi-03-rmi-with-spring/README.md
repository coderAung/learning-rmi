## Learning RMI (using with Spring)

In this project, I created a simple console app to learn and practice Java's RMI.

In the project, there includes 3 main package (should be multi modules with maven, but I avoid it for simplicity for now)
- api (which will be used as commons from both client and server)
- server (which used spring and implements remote services)
- client (where user interface lies and RMI invocations works)

The project is just a simple CRUD with Student as domain, but focus on automating binding rmi object and registering so we can reduce boiler plates.

Using Spring enables me to use advantages of Spring's IOC container and can easily adapt to other Spring's elements such as Spring Data JPA, Transaction Management etc.

I also use Spring Bean Lifecycle to automate RMI objects' binding and unbinding process.

In client module (which is a package in this project), There are two utility classes related to RMI.
- RMIUtils
- RMIFunctions

#### RMIUtilS

RMIUtils is used as a template pattern to lookup rmi objects, moreover, it has safecall method which catch RemoteException inside.
