# Architecture
The architecture is composed by these services:

```kroki-plantuml no-transparency=false
@startuml
left to right direction

node VPS {
    node Docker {
        cloud "Public network" {
            rectangle "API Gateway" as ag
        }

        cloud "Private network" {
            node Doc as doc
            node Account as acc
            node Postgresql as pg
            node Jenkins as j
            node Dind as d
            node Registry as r
        }
    }
}
ag -- acc
ag -- doc
ag -- j
j -- d
j -- r
acc -- pg
@enduml
```
