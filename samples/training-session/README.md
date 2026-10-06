# Training session — Java domain sample

A small example prepared for Bruno Aguiar's portfolio, inspired by the training domain discussed in the [Hercul V2 case study](../../CASE_STUDIES.md#hercul-v2). This is a new, standalone example written with coding-assistant support. It is **not extracted application source**, a new product, or evidence that these exact rules are used by Hercul V2.

## Run

Requires JDK 21 or newer. From this directory:

```sh
sh check.sh
```

The script compiles with Java 21 compatibility, runs 23 checks and removes its temporary build directory. It needs no database, account, Maven, network connection or third-party library.

## Behaviour

- Start an empty, active session.
- Record sets with an exercise, positive repetitions and non-negative external load.
- Calculate repetitions × external load using exact decimal arithmetic.
- Complete a session after at least one set; reject subsequent changes or another completion.
- Preserve previous states with immutable snapshots and defensive list copying.

Zero external load is valid for bodyweight movements. The total only describes external-load volume; it is **not** a physiological workload estimate or training recommendation.

## Why this design

The domain object enforces its rules regardless of the caller. Records and immutable lists keep state changes explicit. `BigDecimal` avoids binary floating-point rounding for entered loads. The executable checks cover invalid input, state transitions, decimal calculations and mutation isolation.

This deliberately small example exposes the domain layer. A real API would also need persistence, authentication, request validation and error mapping, concurrency handling and integration tests. None of those are claimed by this sample, and no Spring Boot or Angular implementation is included here.

## Review

Read `src/TrainingSession.java`, then the behavioural cases in `src/TrainingSessionTest.java`. The companion Hercul V2 visual walkthrough shows the existing application's Angular interface separately; it does not demonstrate this sample being integrated into the private application.
