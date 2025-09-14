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
            node "Account Web" as acc_web
            node Account as acc
            node Jenkins as j
            node Dind as d
            node Registry as r
        }
    }
}
ag -- acc_web
ag -- acc
ag -- doc
ag -- j
j -- d
j -- r
@enduml
```
