#!/usr/bin/env bash
cd /home/application/
./wait-for-it.sh "event-store:2113" -- echo "event-store 2113 is up"
curl -v --request POST  --url  'http://event-store:2113/projections/continuous?name=gather-customer%26type=js%26enabled=true%26emit=true%26trackemittedstreams=true' --header 'authorization: Basic YWRtaW46Y2hhbmdlaXQ=' --data-binary "@resources/gather-aggregate.js"
curl -v --request PUT  --url  'http://event-store:2113/subscriptions/Customer/CustomerSubscriptionGroup' --header 'authorization: Basic YWRtaW46Y2hhbmdlaXQ=' --header 'content-type: application/json' --data-binary "@resources/customer-subscription-group.json"
curl -v --request POST  --url 'http://event-store:2113/projection/$by_category/command/enable' --header 'authorization: Basic YWRtaW46Y2hhbmdlaXQ=' --data '{}'