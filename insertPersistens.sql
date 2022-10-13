use x;

--

insert into OrderType values('Rent', 'rented order');
insert into OrderType values('Sale', 'sale order');

insert into Invoice values(1, 20080712, 1500); 
insert into Invoice values(2, 20090909, 2000);
insert into Invoice values(3, 20091212, 999.99);

insert into ZipcodeCity values(9000, 'Aalborg');
insert into ZipcodeCity values(7700, 'Thisted');
insert into ZipcodeCity values(8800, 'Viborg');
insert into ZipcodeCity values(9492, 'Blokhus');
insert into ZipcodeCity values(8450, 'Hammel');
insert into ZipcodeCity values(8781, 'Stenderup');

insert into Customer values(1, 'Default', 'WS Ltd', 8800, '+45 11111111', 123456-0000, NULL);
insert into Customer values(2, 'Jane Doe', 'Vejgade 2', 7700, '+45 97971010', 1234560001, NULL);
insert into Customer values(3, 'Vestbjerg Byggemarked', 'E45', 9000, '+45 88330000', NULL, 87654321)

insert into [Order] values(1, 20080712, 'delivered', 'Sale', 1, 1);
insert into [Order] values(2, 20090909, 'delivered', 'Sale', 2, 1);
insert into [Order] values(3, 20091212, 'delivered', 'Sale', 3, 2);

insert into Supplier values(1, 'Wild West Supplies', 'Markvej 100', 'DK', '+45 71710202', 'WWS@mail.com', 9492);

insert into StorageLocation values(1, 'Festivalpladsen 1', 8450, 9.5, 'DK');
insert into StorageLocation values(2, 'Gl. Industrivej 3', 8800, 152.3, 'DK');

insert into Product values(1, 'Stor gøb', 250.51, 299.99, 240.0, NULL, 1);
insert into Product values(2, 'Pistolbælte', 79.99, 119.19, 84.45, NULL, 1);
insert into Product values(3, 'Cowboyhat', 151.0, 165.29, 149.99, NULL, 1);

insert into GunReplica values(1, '.44', 'silikone');
insert into Equipment values(2, 'bælte', 'med to pistolhylstre');
insert into Clothes values(3, 'XXS', 'gul');

update Product
set productType = 'GunReplica'
where product.productID = 1;

update Product
set productType = 'Equipment'
where product.productID = 2;

update Product
set productType = 'Clothes'
where product.productID = 3;

insert into Stock values(1, 3, 100, 10, 150);
insert into Stock values(1, 2, 75, 10, 100);
insert into Stock values(1, 1, 15, 5, 20);
insert into Stock values(2, 3, 500, 150, 600);
insert into Stock values(2, 2, 200, 100, 500);
insert into Stock values(2, 1, 150, 25, 300);

insert into OrderLine values(1, 1, 599.8, 2);
insert into OrderLine values(2, 3, 165.29, 1);
insert into OrderLine values(2, 2, 119.19, 1);
insert into OrderLine values(2, 1, 299.99, 1);
insert into OrderLine values(3, 2, 595.95, 5);