##Service Architecture Framework

Geschreven door Rob Vens.

The Service Architecture Framework or SAF describes a simple implementation in plain Java of a publish-subscribe mechanism for domain objects. This is a simple and effective strategy to implement a plugin-like architecture for domain driven design (DDD). These POJO's can then be effectively used to implement the business logic in (micro-) services, with technical services subscribing to domain changes to fetch and persist data. Adapters are used as a layered line-of-defence to isolate the domain objects.

We have written about [Business Centred Architectures](http://www.robvens.com/business-centred-architecturen-i/) (in Dutch: Business Centred Architecturen) in which you could see some examples using Smalltalk, where this pattern originally comes from.

An article containing an introduction to this framework can be found at: http://www.robvens.com/en/service-architecture-framework/
