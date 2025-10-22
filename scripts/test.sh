#!/bin/bash
set -e

API_URL="http://localhost:8080/api/payments"
echo "==============================================="
echo "✅ Positive Test - AcquirerA (Even BIN sum)"
curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
        "cardNumber": "4000000000000002",
        "expiry": "12/26",
        "cvv": "123",
        "amount": 100.0,
        "currency": "USD",
        "merchantId": "M123"
      }'
echo -e "\n"

echo "✅ Positive Test - AcquirerB (Odd BIN sum)"
curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
        "cardNumber": "4111111111111111",
        "expiry": "11/25",
        "cvv": "123",
        "amount": 50.0,
        "currency": "EUR",
        "merchantId": "M456"
      }'
echo -e "\n"

echo "❌ Validation Failures"

# Invalid card number (fails Luhn)
curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
        "cardNumber": "1234567890123456",
        "expiry": "12/26",
        "cvv": "123",
        "amount": 100,
        "currency": "USD",
        "merchantId": "M123"
      }'
echo -e "\n"

# Invalid expiry
curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
        "cardNumber": "4111111111111112",
        "expiry": "13/25",
        "cvv": "123",
        "amount": 100,
        "currency": "USD",
        "merchantId": "M123"
      }'
echo -e "\n"

# Invalid CVV
curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
        "cardNumber": "4111111111111112",
        "expiry": "12/25",
        "cvv": "12",
        "amount": 100,
        "currency": "USD",
        "merchantId": "M123"
      }'
echo -e "\n"

# Invalid amount
curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
        "cardNumber": "4111111111111112",
        "expiry": "12/25",
        "cvv": "123",
        "amount": -10,
        "currency": "USD",
        "merchantId": "M123"
      }'
echo -e "\n"

# Invalid currency
curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
        "cardNumber": "4111111111111112",
        "expiry": "12/25",
        "cvv": "123",
        "amount": 100,
        "currency": "US",
        "merchantId": "M123"
      }'
echo -e "\n"

# Missing merchantId
curl -s -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
        "cardNumber": "4111111111111112",
        "expiry": "12/25",
        "cvv": "123",
        "amount": 100,
        "currency": "USD",
        "merchantId": ""
      }'
echo -e "\n"
