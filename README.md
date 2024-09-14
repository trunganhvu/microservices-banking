# Microservice
## Port Service
* Account: 9211
* Payment: 9212
* PaymentProcessingService: 9213
* Profile: 9214

## Database
* Account Service: accountdb - 192.168.1.11:3306/accountdb
* Profile Service: profiledb - 192.168.1.11:3306/profiledb
* Payment Service: paymentdb - 192.168.1.11:3306/paymentdb

## Kafka
### Kafka docker
```sh
sudo docker compose -f kafka-docker-compose.yml up -d
```
### Kafka server
* Bootstrap server: 192.168.1.11:9092

### Kafka topic
* paymentCompleted
* paymentCreated
* paymentRequest
* profileOnboarded
* profileOnboarding



# API Get all profile
```sh
curl --location --request GET 'http://localhost:9214/api/v1/profiles' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "E2@email.com",
    "name": "E",
    "initialBalance": 10,
    "role": "DEVELOPER"
}'
```

Response:
```sh
[
    {
        "id": 1,
        "email": "a@email.com",
        "status": "ACTIVE",
        "name": "A",
        "initialBalance": 0.0,
        "role": "DEVELOPER"
    },
    ...
    {
        "id": 5,
        "email": "E2@email.com",
        "status": "PENDING",
        "name": "E",
        "initialBalance": 0.0,
        "role": "DEVELOPER"
    }
]
```

# API: Check duplicate email
```sh
curl --location --request GET 'http://localhost:9214/api/v1/profiles/checkDuplicate/a@email.com' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "E2@email.com",
    "name": "E",
    "initialBalance": 10,
    "role": "DEVELOPER"
}'
```

# API: Create profile
```sh
curl --location 'http://localhost:9214/api/v1/profiles' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "E2@email.com",
    "name": "E",
    "initialBalance": 10,
    "role": "DEVELOPER"
}'
```

profiledb: Table profile
id	email	name	status	role
5	E2@email.com	E	PENDING	DEVELOPER

accountdb: Table account
id	email	currency	balance	reserved	version
d2e99097-ac99-412a-b584-0c7a484f022a	E2@email.com	USD	10	0	0


# API Check balance by account
```sh
curl --location 'http://localhost:9211/api/v1/accounts/checkBalance/d2e99097-ac99-412a-b584-0c7a484f022a'
```

# API Create payment
```sh
curl --location 'http://localhost:9212/api/v1/payments/payment' \
--header 'Content-Type: application/json' \
--data '{
    "accountId": "d2e99097-ac99-412a-b584-0c7a484f022a",
    "amount": 1.0
}'
```
