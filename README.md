WEB project

# Site of store of the sports club "Orion"

### Author: Aliaksandr Artsikhovich

## Description

The project represents the website of store of the sports club "Orion".
The website provides an opportunity for guests to get information about sport
products. Guests also can register or login as customer/manager/administrator.
Customers can make an order, which can be approved or rejected by a manager
of the company. The administrator maintains the website's catalog and
user's accounts.

Web page diagram - https://octopus.do/w6z0k35l8ep

## Actors

### Guest

An unauthorized visitor to the website is considered a guest and can change 
the language of the site, visit product information pages, search product catalogs,
register, and then log in if you already have an account.

* Allowed actions:
    * Visit the main page
    * Browse catalogs
    * Register
    * Login
    * Change website language

### Customer

An authorized user with minimal privileges - a customer. Customer can do
everything, that is allowed to the guest, add products to the personal cart,
remove products from the cart, make an order from his cart, view a personal
profile information, view personal order's history, and remove order if still
not approved.

* Allowed actions:
    * Add a product to the cart
    * View the personal cart
    * Remove a product from the cart
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information

    * Visit the main page
    * Browse catalogs
    * Register
    * Login
    * Change website language

### Manager

The manager is an authorized user - an Orion club employee. He is responsible for
order management and can view a list of all orders of users, can approve, reject
and delete orders. Also has all privileges of a customer and a guest.

* Allowed actions:
    * View list of orders of all users
    * Reject or approve orders
    * Remove rejected orders

    * Add a product to the cart
    * View the personal cart
    * Remove a product from the cart
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit the main page
    * Browse catalogs
    * Register
    * Login
    * Change website language

### Administrator

Administrator - is an authorized user responsible for website administration.
Can view list of all users, set user's privileges level (customer/manager/
administrator/club member), delete or create user's accounts. Can't reach out
user's password (stored encrypted). Can view a list of all products in catalogs
and edit them, as well as add new products, upload images, add product description,
delete products from the catalog. Has all privileges of a guest and a customer, 
but don't have privileges of a manager, but can register a user with a manager's role.

* Allowed actions:
    * Add new products
    * View list of all user's accounts
    * Change privileges for existing user's accounts
    * Delete users
    * Create new users
    * View list of all products in the catalog
    * Edit existing products
    * Upload images
    * Add product description
    * Delete products from the catalog

    * Add a product to the cart
    * View the personal cart
    * Remove a product from the cart
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit the main page
    * Browse catalogs
    * Register
    * Login
    * Change website language


### Club member

User with client rights who is a member of the Orion sports club. A club member can
do everything that is allowed to the client, but when ordering goods, he has 
the opportunity to receive a discount set by the administrator. The club member role
is set to the client and removed by the administrator. 

* Allowed actions:
    * Add a product to the cart
    * View the personal cart
    * Remove a product from the cart
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit the main page
    * Browse catalogs
    * Register
    * Login
    * Change website language

