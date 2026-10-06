# Project case studies

Engineering summaries by Bruno Aguiar · October 2026

These summaries describe personal projects at a high level. Application source repositories remain private. They contain no source code, private user data, credentials or deployment configuration. Features and tooling below reflect project documentation; the initial documentation review did not execute the applications' test suites or establish performance, usage or business metrics. A later focused guest-interface check is described in the linked visual walkthrough; it does not validate backend or private account flows.

## Hercul V2

[Visual walkthrough of the Angular interface](VISUAL_WALKTHROUGH.md) · [Independent Java domain sample](https://github.com/bruno1251985/bruno1251985/blob/main/samples/training-session/README.md)

**Focus:** full-stack development, incremental rebuilding and domain modelling.

### Problem and scope

Rebuild an existing fitness application gradually while learning to give business rules a clear place in the architecture. The project uses the earlier product as a functional reference and implements capabilities in increments. It is an ongoing learning project.

### Implementation approach

An Angular and TypeScript frontend communicates with a Java and Spring Boot API backed by PostgreSQL. The documented scope includes identity, profiles, onboarding, training, nutrition and progress tracking. Backend features group related behaviour, with separate responsibilities for HTTP entry points, use cases, domain rules and persistence.

### Decisions and trade-offs

- **Separate frontend and API:** supports richer client interactions and a distinct backend model, at the cost of coordinating contracts, builds and validation across two applications.
- **Feature-oriented modules and domain boundaries:** keep related behaviour together and isolate business rules from delivery and persistence concerns. This adds structure that needs to remain proportionate to the project's size.
- **Versioned database migrations:** make schema evolution explicit as features grow.
- **Incremental delivery:** keeps each change reviewable and makes it possible to compare a small feature against the reference product before expanding scope.

### Quality and current outcome

Project documentation includes backend verification, domain coverage checks, frontend tests, internationalisation checks and builds, alongside architecture decisions and specifications. The documented result is a multi-feature application under active development; no claim is made here about complete feature parity or production readiness.

### Interview discussion

Walk through a profile use case from a user interaction to a business rule and persistence, using a conceptual diagram. Discuss where validation belongs, how a feature boundary is chosen, and how an incremental rebuild avoids coupling every change to the whole product. Any demonstration should use synthetic data.

## Oukio

**Focus:** Java backend development, data correctness and long-term maintainability.

### Problem and scope

Create a personal assistant for finances, goals, tasks and daily routines that can be maintained by one person over time. The product favours connected workflows and a manageable operational footprint.

### Implementation approach

The project uses Java and Spring Boot with server-rendered Thymeleaf pages, PostgreSQL, explicit SQL access and Flyway migrations. Documented capabilities include financial records and imports, categorisation, budgets, analysis, an integrated agenda and a contextual assistant.

### Decisions and trade-offs

- **One application with server-rendered pages:** reduces the number of independently built and deployed components. The trade-off is less separation between client and server development than a dedicated SPA would provide.
- **Explicit SQL:** makes database behaviour visible and reviewable, while requiring careful query design and maintenance.
- **PostgreSQL-based integration tests:** exercise database-specific behaviour and migrations rather than relying only on an in-memory substitute. Container-based tests introduce a Docker dependency.
- **Portable container deployment and restore verification:** treat operation and recovery as part of maintainability. Portability still requires disciplined configuration and ongoing maintenance.

### Quality and current outcome

The project documentation describes an implemented finance core and agenda, integration tests using Testcontainers, and a backup restoration verification flow. These are documented engineering practices, not independently measured guarantees of reliability or security.

### Interview discussion

Explain why a small product might choose server-rendered pages over a separate frontend. Discuss how to test an import against a real database, how schema changes are handled, and why a backup needs a restore check. Use fictional transactions and avoid showing real account details.

## Public website: Hercul-site

[Hercul-site](https://github.com/bruno1251985/Hercul-site) is the public HTML website repository. It provides a small, inspectable web artifact alongside these summaries of the private applications. The live website can evolve independently of that repository.

[Return to profile](https://github.com/bruno1251985)
