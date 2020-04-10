Feature: postman-v2

	Scenario: Market
# List Orderbooks
		Given url 'https://{{domain}}/api/orderbooks?'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Tickers
		Given url 'https://{{domain}}/api/tickers?'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Ticker
		Given url 'https://{{domain}}/api/tickers/:orderbook_code'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Market
		Given url 'https://{{domain}}/api/markets/{orderbook_code}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Market Transactions
		Given url 'https://{{domain}}/api/transactions?filter[orderbook_code]={orderbook_code}&filter[from]={hours}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Market Transactions Archive
		Given url 'https://{{domain}}/api/transactions/archive?filter[orderbook_code]={orderbook_code}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Transaction
		Given url 'https://{{domain}}/api/transactions/:id'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Market Candles
		Given url 'https://{{domain}}/api/candles?filter[orderbook_code]={orderbook_code}&filter[days]={days}&span={span}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

	Scenario: Trading
# Bids (Buy orders)
# Show Bid
		Given url 'https://{{domain}}/api/bids/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Bids
		Given url 'https://{{domain}}/api/bids?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Bid
		Given url 'https://{{domain}}/api/bids'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "bids",        "attributes": {            "amount": 100,            "price": 12,            "orderbook_code": "btc_usd"        },        "relationships": {          "user": {            "data": {              "id": 1,              "type": "users"            }          }        }    }}    
		When method POST

# Cancel Bid
		Given url 'https://{{domain}}/api/bids/:id/cancel'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request 
		When method POST

# Asks (Sell orders)
# Show Ask
		Given url 'https://{{domain}}/api/asks/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Asks
		Given url 'https://{{domain}}/api/asks?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Ask
		Given url 'https://{{domain}}/api/asks'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {      "type": "asks",      "attributes": {        "amount": 0.2,        "price": 150,        "orderbook_code": "btc_usd"        },      "relationships": {        "user": {          "data": {            "id": 1,            "type": "users"          }        }      }    }}
		When method POST

# Cancel Ask
		Given url 'https://{{domain}}/api/asks/:id/cancel'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request 
		When method POST

# Trading Bots
# Buying Bot
# List Buying Bots
		Given url 'https://{{domain}}/api/buying_bots?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Buying Bot
		Given url 'https://{{domain}}/api/buying_bots/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Buying Bot
		Given url 'https://{{domain}}/api/buying_bots'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "buying_bots",    "attributes": {      "amount": 100,      "orderbook_code": "btc_usd"    },    "relationships": {      "user": {        "data": {          "id": 1,          "type": "users"        }      }    }  }}
		When method POST

# Cancel Buying Bot
		Given url 'https://{{domain}}/api/buying_bots/:id/cancel'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request 
		When method POST

# Selling Bot
# List Selling Bots
		Given url 'https://{{domain}}/api/selling_bots?'
		And header Authorization = '{{your_api_key}}'
		And header Content-Type = 'application/json'
		When method GET

# Show Selling Bot
		Given url 'https://{{domain}}/api/selling_bots/:id'
		And header Authorization = '{{your_api_key}}'
		And header Content-Type = 'application/json'
		When method GET

# Create Selling Bot
		Given url 'https://{{domain}}/api/selling_bots'
		And header Authorization = '{{your_api_key}}'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "selling_bots",    "attributes": {      "amount": 100,      "orderbook_code": "btc_usd"    },    "relationships": {      "user": {        "data": {          "id": 1,          "type": "users"        }      }    }  }}
		When method POST

# Cancel Selling Bot
		Given url 'https://{{domain}}/api/selling_bots/:id/cancel'
		And header Authorization = '{{your_api_key}}'
		And header Content-Type = 'application/json'
		And request 
		When method POST

# Buys
# List Buys
		Given url 'https://{{domain}}/api/buys?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Buy
		Given url 'https://{{domain}}/api/buys/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Sells
# List Sells
		Given url 'https://{{domain}}/api/sells?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Sell
		Given url 'https://{{domain}}/api/sells/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Executing Bids & Asks
		Given url 'https://{{domain}}/api/orders?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Cancel All Orders
		Given url 'https://{{domain}}/api/orders/cancel?filter[orderbook_code]={orderbook_code}'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request 
		When method POST

# List Trades
		Given url 'https://{{domain}}/api/trades?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

	Scenario: Wallets
# List Cash Wallets
		Given url 'https://{{domain}}/api/cash_wallets?page[per_page]={requests_per_page}'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Cash Wallet
		Given url 'https://{{domain}}/api/cash_wallets/:id|currency}'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Coin Wallets
		Given url 'https://{{domain}}/api/coin_wallets?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Coin Wallet
		Given url 'https://{{domain}}/api/coin_wallets/:id|currency}'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

	Scenario: Deposit
# List Cash Deposits
		Given url 'https://{{domain}}/api/cash_deposits?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Cash Deposit
		Given url 'https://{{domain}}/api/cash_deposits/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Cash Deposit Instructions
		Given url 'https://{{domain}}/api/cash_deposit_instructions?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Coin Deposits
		Given url 'https://{{domain}}/api/coin_deposits?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Coin Deposits
		Given url 'https://{{domain}}/api/coin_deposits/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Bitcoin Addresses
		Given url 'https://{{domain}}/api/bitcoin_addresses?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Bitcoin Address
		Given url 'https://{{domain}}/api/bitcoin_addresses/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Announce Cash Deposit
		Given url 'https://{{domain}}/api/cash_deposits'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "cash_deposits",    "attributes": {      "requested_amount": 1000,      "deposit_method": "bank"    },    "relationships": {      "user": {        "data": {          "id": 1,          "type": "users"        }      }    }  }}
		When method POST

	Scenario: Withdraw
# Withdrawal Instructions
# List Withdrawal Instructions
		Given url 'https://{{domain}}/api/withdrawal_instructions?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Withdrawal Instructions
		Given url 'https://{{domain}}/api/withdrawal_instructions/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Withdrawal Instructions
		Given url 'https://{{domain}}/api/withdrawal_instructions'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "withdrawal_instructions",    "attributes":{      "label": "primeras-instrucciones",      "payment_method_type": "ar_domestic_bank",      "body": {        "account_type": "savings",        "cbu": "0150553621000111396333"      }    },    "relationships": {      "user": {        "data": {          "id": 1,          "type": "users"        }      }    }  }}
		When method POST

# Delete Withdrawal Instructions
		Given url 'https://{{domain}}/api/withdrawal_instructions/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request 
		When method DELETE

# List Cash Withdrawals
		Given url 'https://{{domain}}/api/cash_withdrawals?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Cash Withdrawal
		Given url 'https://{{domain}}/api/cash_withdrawals/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Cash Withdrawal
		Given url 'https://{{domain}}/api/cash_withdrawals'
		And header Authorization = '{{your_api_key}}'
		And header One-Time-Password = 'valid_otp'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "cash_withdrawals",    "attributes": {      "amount": "100",      "fiat_code": "ars"    },    "relationships": {      "withdrawal_instruction": {        "data": {          "id": "22",          "type": "withdrawal_instructions"        },        "user": {          "data": {            "id": 1,            "type": "users"          }        }      }    }  }}
		When method POST

# List Coin Withdrawals
		Given url 'https://{{domain}}/api/coin_withdrawals?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Coin Withdrawal
		Given url 'https://{{domain}}/api/coin_withdrawals/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Coin Withdrawal
		Given url 'https://{{domain}}/api/coin_withdrawals'
		And header Authorization = '{{your_api_key}}'
		And header One-Time-Password = 'valid_otp'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "coin_withdrawals",        "attributes": {            "amount": 12.3456789,            "coin_code": "btc",            "label": "Trezor",            "to_addresses": "mszEUK9E6E7n4SNcrjYH8Fr7ZTGP9n3dRb"        },        "relationships": {          "user": {            "data": {              "id": 1,              "type": "users"            }           }        }    }}
		When method POST

	Scenario: Transfers
# List  Sent Transfers
		Given url 'https://{{domain}}/api/sent_transfers?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Sent Transfer
		Given url 'https://{{domain}}/api/sent_transfers/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Received Transfers
		Given url 'https://{{domain}}/api/received_transfers?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Received Transfer
		Given url 'https://{{domain}}/api/received_transfers/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

	Scenario: Merchant
# List Payments
		Given url 'https://{{domain}}/api/merchants/payments?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Payment
		Given url 'https://{{domain}}/api/merchants/payments/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Payment
		Given url 'https://{{domain}}/api/merchants/payments'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "payments",        "relationships": {            "user": {                "data": {                    "id": 692,                    "type": "users"                }            }        },        "attributes": {            "amount": 100,            "currency_code": "USD",            "keep": 10,            "callback_url": "https://mystore.com/webhook",            "customer_reference": "Purchase at My Store",            "merchant_reference": "Sale id: 2212"        }    }}
		When method POST

# Setup Point of Sale
		Given url 'https://{{domain}}/api/merchants/pos'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "pos",    "attributes": {      "merchant_keep": 10,      "merchant_logo": "https://mystore.com/logo.png",      "merchant_name": "My Store",      "merchant_site": "https://mystore.com",      "merchant_slug": "my-store"    }  }}
		When method POST

	Scenario: Api Keys
# List Api Keys
		Given url 'https://{{domain}}/api/api_keys?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Api Key
		Given url 'https://{{domain}}/api/api_keys/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Api Key
		Given url 'https://{{domain}}/api/api_keys'
		And header Authorization = '{{your_api_key}}'
		And header One-Time-Password = 'valid_otp'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "api_keys",    "attributes": {    	"fund_account": false,        "create_withdrawal": false,        "get_withdrawal": false,        "get_balances": true,        "trading": false,        "payments": false,        "compliance": false,        "api_keys": false    }  }}
		When method POST

# Delete Api Key
		Given url 'https://{{domain}}/api/api_keys/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request 
		When method DELETE

	Scenario: Users
# List Users
		Given url 'https://{{domain}}/api/users?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.1'
		And header Content-Type = 'application/json'
		When method GET

# Show User
		Given url 'https://{{domain}}/api/users/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.1'
		And header Content-Type = 'application/json'
		When method GET

# Create User
		Given url 'https://{{domain}}/api/users'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.1'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "users",        "attributes": {}    }}
		When method POST

# Update User
		Given url 'https://{{domain}}/api/users/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.1'
		And header Content-Type = 'application/json'
		And request {    "data": {        "id": "5",        "type": "users",        "attributes": {            "email": "anotheremail@gmail.com",            "do_not_email": true,            "password": "newpassword",            "password_confirmation": "newpassword"        }    }}
		When method PATCH

# List Accounts
		Given url 'https://{{domain}}/api/accounts'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

	Scenario: Compliance
# Issues
# List Issues
		Given url 'https://{{domain}}/api/issues?'
		And header Authorization = '{{your_api_key}}'
		When method GET

# Show Issue
		Given url 'https://{{domain}}/api/issues/:issue_id?'
		And header Authorization = '{{your_api_key}}'
		When method GET

# Create Issue
		Given url 'https://{{domain}}/api/issues'
		And header Authorization = '{{your_api_key}}'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "issues",    "attributes": {      "reason_code": "new_client"    },    "relationships": {      "issue": {        "data": {           "type": "User",          "id": 2        }      }    }  }}
		When method POST

# Set Complete
		Given url 'https://{{domain}}/api/issues/:issue_id/complete'
		And header Authorization = '{{your_api_key}}'
		When method POST

# Seeds
# Create Natural Docket
		Given url 'https://{{domain}}/api/natural_docket_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "natural_docket_seeds",    "attributes": {      "first_name": "John",      "last_name": "Doe",      "nationality": "AR",      "gender_code": "male",      "marital_status_code": "single",      "politically_exposed": "false",      "birth_date": "1990-12-31"    },    "relationships": {      "issue": {        "data": {          "id": "2",          "type": "issues"        }      }    }  }}
		When method POST

# Update Natural Docket
		Given url 'https://{{domain}}/api/natural_docket_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "natural_docket_seeds",        "attributes": {            "first_name": "John",            "last_name": "Doe",            "nationality": "AR",            "gender_code": "male",            "marital_status_code": "single",            "politically_exposed": "false",            "birth_date": "1990-12-31"        },        "relationships": {            "issue": {                "data": {                    "id": "2",                    "type": "issues"                }            }        }    }}
		When method PATCH

# Create Legal Entity Docket
		Given url 'https://{{domain}}/api/legal_entity_docket_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "legal_entity_docket_seeds",    "attributes": {      "industry": "it",      "business_description": "software",      "country":"AR",      "commercial_name": "AwesomeCompany",      "legal_name": "My Company Inc."    },    "relationships": {      "issue": {        "data": {           "type": "issues",          "id": "some_issue.id"         }      }    }  }}
		When method POST

# Update Legal Entity Docket
		Given url 'https://{{domain}}/api/legal_entity_docket_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type": "legal_entity_docket_seeds",		"attributes": {			"industry": "it",			"business_description": "software",			"country":"AR",			"commercial_name": "AwesomeCompany",			"legal_name": "My Company Inc."		}	}}
		When method PATCH

# Create Identification
		Given url 'https://{{domain}}/api/identification_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type":"identification_seeds",    "attributes": {      "identification_kind_code": "national_id",      "issuer": "AR",      "number": "12345678"    },    "relationships": {      "issue": {        "data": {           "type": "issues",          "id": "some_issue.id"         }      }    }  }}
		When method POST

# Update Identification
		Given url 'https://{{domain}}/api/identification_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"identification_seeds",		"attributes": {			"identification_kind_code": "national_id",			"issuer": "AR",			"number": "12345678"		}	},	 }
		When method PATCH

# Create Note
		Given url 'https://{{domain}}/api/note_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "note_seeds",        "attributes": {            "body": "these are custom notes",            "title": "identification note"        },        "relationships": {            "issue": {                "data": {                    "type": "issues",                    "id": "some_issue.id"                }            }        }    }}
		When method POST

# Update Note
		Given url 'https://{{domain}}/api/note_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"note_seeds",		"attributes": {			"body": "these are custom notes",			"title": "identification note"		}	},	 }
		When method PATCH

# Create Domicile
		Given url 'https://{{domain}}/api/domicile_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"domicile_seeds",		"attributes": {			"city": "CABA",			"country": "AR",			"floor": "0",			"postal_code": "1001",			"street_address": "Balcarce",			"street_number": "50"		},		"relationships": {			"issue": {				"data": {					"id": "2",					"type": "issues"				}			}		}	}}
		When method POST

# Update domicile
		Given url 'https://{{domain}}/api/domicile_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"domicile_seeds",		"attributes": {			"city": "CABA",			"country": "AR",			"floor": "0",			"postal_code": "1001",			"street_address": "Balcarce",			"street_number": "50"		},		"relationships": {			"issue": {				"data": {					"id": "2",					"type": "issues"				}			}		}	}}
		When method PATCH

# Create Email
		Given url 'https://{{domain}}/api/email_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"email_seeds",		"attributes": {			"address": "person@example.com",			"email_kind_code": "invoicing"		}	},		"relationships": {			"issue": {				"data": {					"id": "2",					"type": "issues"				}			}		}}
		When method POST

# Update Email
		Given url 'https://{{domain}}/api/email_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"email_seeds",		"attributes": {			"address": "person@example.com",			"email_kind_code": "invoicing"		}	},	 }
		When method PATCH

# Create Argentina Invoicing Detail
		Given url 'https://{{domain}}/api/argentina_invoicing_detail_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type":"argentina_invoicing_detail_seeds",    "attributes": {      "address": "Balcarce, 50, 0, CABA",      "country": "AR",      "full_name": "John, Doe",      "receipt_kind_code": "b",      "tax_id": "20123456786",      "tax_id_kind_code": "cuil",      "vat_status_code": "consumidor_final"    },    "relationships": {      "issue": {        "data": {          "id": "2",          "type": "issues"        }      }    }  }}
		When method POST

# Update Argentina Invoicing Detail
		Given url 'https://{{domain}}/api/argentina_invoicing_detail_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"argentina_invoicing_detail_seeds",		"attributes": {			"address": "Balcarce, 50, 0, CABA",			"country": "AR",			"full_name": "John, Doe",			"receipt_kind_code": "b",			"tax_id": "20123456786",			"tax_id_kind_code": "cuil",			"vat_status_code": "consumidor_final"		}	},	 }
		When method PATCH

# Create Chile Invoicing Detail
		Given url 'https://{{domain}}/api/chile_invoicing_detail_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"argentina_invoicing_detail_seeds",		"attributes": {			"ciudad": "santiago",			"comuna": "Chile",			"giro": "2332",			"tax_id": "12053214-6",			"vat_status_code": "consumidor_final"		},		"relationships": {			"issue": {				"data": {					"id": "2",					"type": "issues"				}			}		}	}}
		When method POST

# Update Chile Invoicing Detail
		Given url 'https://{{domain}}/api/chile_invoicing_detail_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"argentina_invoicing_detail_seeds",		"attributes": {			"ciudad": "santiago",			"comuna": "Chile",			"giro": "2332",			"tax_id": "12053214-6",			"vat_status_code": "consumidor_final"		}	}}
		When method PATCH

# Create Allowance
		Given url 'https://{{domain}}/api/allowance_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"allowance_seeds",		"attributes": {			"kind_code": "usd"		},		"relationships": {			"issue": {				"data": {					"id": "2",					"type": "issues"				}			}		}	}}
		When method POST

# Update Allowance
		Given url 'https://{{domain}}/api/allowance_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"allowance_seeds",		"attributes": {			"kind_code": "usd"		}	},	 }
		When method PATCH

# Create Phone
		Given url 'https://{{domain}}/api/phone_seeds'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"phone_seeds",		"attributes": {			"country": "AR",			"number": "12345678",			"phone_kind_code": "main"		}	},		"relationships": {			"issue": {				"data": {					"id": "2",					"type": "issues"				}			}		}}
		When method POST

# Update Phone
		Given url 'https://{{domain}}/api/phone_seeds/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {	"data": {		"type":"phone_seeds",		"attributes": {			"country": "AR",			"number": "12345678",			"phone_kind_code": "main"		}	},	 }
		When method PATCH

# Create Attachments
		Given url 'https://{{domain}}/api/attachments'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "attachments",        "attributes": {            "document": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNk+P+/HgAFhAJ/wlseKgAAAABJRU5ErkJggg==",            "document_file_name": "pixel.png",            "document_content_type": "image/png",            "document_size": "68"        },        "relationships": {            "attached_to_seed": {                "data": {                    "id": "1",                    "type": "identification_seeds"                }            }        }    },    "included": [        {            "type": "identification_seeds",            "attributes": {                "number": "12345678",                "issuer": "AR",                "identification_kind_code": "national_id",                "created_at": "2018-10-23T17:32:52.000Z",                "updated_at": "2018-10-23T17:32:52.000Z"            },            "relationships": {                "attachments": {                    "data": []                }            },            "id": "1"        }    ]}
		When method POST

	Scenario: CrossBorder
# Ports
# List Concierge Ports
		Given url 'https://{{domain}}/api/concierge_ports?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Request
# Request Webhooks
# Create a Concierge Request
		Given url 'https://{{domain}}/api/concierge_requests'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "concierge_requests",        "attributes": {            "port_code": "cl_clp",            "external_id": "ABC123",            "payment_authorization_code": "ABC1234DEF5678"        },        "relationships": {            "user": {                "data": {                    "id": 1,                    "type": "users"                }            }        }    }}
		When method POST

# Update a Concierge Request
		Given url 'https://{{domain}}/api/concierge_requests/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "concierge_requests",        "id": "<your_request_id>",        "attributes": {            "external_id": "ABC123",            "payment_authorization_code": "ABC1234DEF5678"        }    }}
		When method PATCH

# Show a Concierge Request
		Given url 'https://{{domain}}/api/concierge_requests/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# List Concierge Requests
		Given url 'https://{{domain}}/api/concierge_requests?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Get a quote for the Request
		Given url 'https://{{domain}}/api/concierge_requests/:id/request_quote'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request 
		When method POST

# Accept the quote
		Given url 'https://{{domain}}/api/concierge_requests/:id/accept_quote'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request 
		When method POST

# Outputs
# Create Concierge Request Output
		Given url 'https://{{domain}}/api/concierge_request_outputs'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {  "data": {    "type": "concierge_request_outputs",    "attributes": {      "port_code": "cl_clp",      "amount": 2200    },    "relationships": {      "request": {        "data": {          "id": "143",          "type": "concierge_requests"        }      },      "withdrawal_instruction": {        "data": {          "id": 322,          "type": "withdrawal_instructions"        }      },      "user": {        "data": {          "id": 692,          "type": "users"        }      }    }  }}
		When method POST

# List Concierge Request Outputs
		Given url 'https://{{domain}}/api/concierge_request_outputs?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Show Concierge Request Output
		Given url 'https://{{domain}}/api/concierge_request_outputs/:id'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Get an estimate
		Given url 'https://{{domain}}/api/concierge_estimates'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "concierge_estimates",        "attributes": {            "port_code": "cl_clp",            "outputs": [                {                    "port_code": "us_usd",                    "budget": 100000                },                {                    "port_code": "mx_mxn",                    "amount": 500                }            ]        }    }}
		When method POST

	Scenario: Miscellaneous
# List Movements
		Given url 'https://{{domain}}/api/movements?'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		When method GET

# Create Support Ticket
		Given url 'https://{{domain}}/api/support_tickets'
		And header Authorization = '{{your_api_key}}'
		And header Version = '2.0'
		And header Content-Type = 'application/json'
		And request {    "data": {        "type": "support_tickets",        "attributes": {            "subject": "Have a custom question",            "body": "How old is Windsor castle?"        },        "relationships": {            "user": {                "data": {                    "id": "1",                    "type": "users"                }            }        }    }}
		When method POST

