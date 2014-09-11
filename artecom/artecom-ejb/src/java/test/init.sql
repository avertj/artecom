-- addresses
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (1, 'Grenoble', 'Pathé Chavant', 38000, '21 Boulevard Maréchal Lyautey');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (2, 'Grenoble', 'Rectorat de Grenoble', 38021, '7 Place Bir Hakeim');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (3, 'Grenoble', 'College Champollion', 38000, '10 Rue François Raoult');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (4, 'Grenoble', 'Le Subway', 38000, '2 Rue Lakanal');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (5, 'Lyon', 'McDonald''s', 69007, '6 Place Gabriel Péri');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (6, 'Lyon', 'La Poste', 69003, '150 Rue Pierre Corneille');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (7, 'Lyon', 'A La Pêche Aux Moules', 69002, '2 Rue des Marronniers');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (8, 'Paris', 'Le Petit Palais', 75008, 'Avenue Winston Churchill');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (9, 'Paris', 'Unisson', 75008, '61 Rue de l''Arcade');
INSERT INTO ARTECOM.ADDRESS (ID, CITY, "NAME", POSTCODE, STREET) 
	VALUES (10, 'Noirmoutier-en-l''Île', 'L''Aquarium', 85330, 'Rue de l''Écluse');

-- craft
INSERT INTO ARTECOM.CRAFT (ID, "NAME", PARENT_ID) 
	VALUES (1, 'Craft 1', NULL);
INSERT INTO ARTECOM.CRAFT (ID, "NAME", PARENT_ID) 
	VALUES (2, 'Craft 2', NULL);
INSERT INTO ARTECOM.CRAFT (ID, "NAME", PARENT_ID) 
	VALUES (3, 'Craft 3', NULL);
INSERT INTO ARTECOM.CRAFT (ID, "NAME", PARENT_ID) 
	VALUES (4, 'Craft 1.1', 1);
INSERT INTO ARTECOM.CRAFT (ID, "NAME", PARENT_ID) 
	VALUES (5, 'Craft 1.2', 1);
INSERT INTO ARTECOM.CRAFT (ID, "NAME", PARENT_ID) 
	VALUES (6, 'Craft 3.1', 3);

--client
INSERT INTO ARTECOM.CLIENT (ID, PROFIL_TYPE, FIRSTNAME, LASTBILLINGADRESS, LASTNAME, LASTSHIPPINGADRESS, LOGIN, DESCRIPTION) 
	VALUES (1, 'C', 'Bob', NULL, 'Azert', NULL, 'client1', NULL);
INSERT INTO ARTECOM.CLIENT (ID, PROFIL_TYPE, FIRSTNAME, LASTBILLINGADRESS, LASTNAME, LASTSHIPPINGADRESS, LOGIN, DESCRIPTION) 
	VALUES (2, 'C', 'Alice', NULL, 'Ubdf', NULL, 'client2', NULL);
INSERT INTO ARTECOM.CLIENT (ID, PROFIL_TYPE, FIRSTNAME, LASTBILLINGADRESS, LASTNAME, LASTSHIPPINGADRESS, LOGIN, DESCRIPTION) 
	VALUES (3, 'C', 'Luce', NULL, 'Pxgn', NULL, 'client3', NULL);
INSERT INTO ARTECOM.CLIENT (ID, PROFIL_TYPE, FIRSTNAME, LASTBILLINGADRESS, LASTNAME, LASTSHIPPINGADRESS, LOGIN, DESCRIPTION) 
	VALUES (4, 'C', 'Bernadette', NULL, 'Cfyujt', NULL, 'client4', NULL);
INSERT INTO ARTECOM.CLIENT (ID, PROFIL_TYPE, FIRSTNAME, LASTBILLINGADRESS, LASTNAME, LASTSHIPPINGADRESS, LOGIN, DESCRIPTION) 
	VALUES (5, 'P', 'Paulette', NULL, 'Mihugybftydt', NULL, 'artisan1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis at pulvinar neque. In blandit sem nec ligula cursus, quis molestie nunc sollicitudin. In eleifend quam nec diam volutpat suscipit. Mauris pellentesque mollis pharetra. Sed ex dui, viverra vel sapien at, eleifend consequat arcu. Vestibulum quis nibh eu elit lobortis finibus. Morbi in vulputate ex. In sit amet sodales ligula. Phasellus viverra magna mattis, ornare augue eu, bibendum leo. Nullam consequat ex in consectetur condimentum. Suspendisse potenti.');
INSERT INTO ARTECOM.CLIENT (ID, PROFIL_TYPE, FIRSTNAME, LASTBILLINGADRESS, LASTNAME, LASTSHIPPINGADRESS, LOGIN, DESCRIPTION) 
	VALUES (6, 'P', 'Maurice', NULL, 'Tdfh-brt', NULL, 'artisan2', 'Nullam tincidunt metus at mollis placerat. Nunc efficitur imperdiet turpis sit amet gravida. Morbi sagittis dui eu erat consectetur ultricies. Aliquam mattis felis eu turpis porta euismod. Donec lobortis nunc velit, nec gravida enim tempor sed. Ut eleifend at orci sit amet venenatis. Vivamus sed volutpat velit, id tincidunt velit. Fusce non sem rutrum, dignissim erat sed, varius tellus. Pellentesque tincidunt sem eget maximus egestas. Nulla volutpat tincidunt ante, in tincidunt lorem ornare vitae.');
INSERT INTO ARTECOM.CLIENT (ID, PROFIL_TYPE, FIRSTNAME, LASTBILLINGADRESS, LASTNAME, LASTSHIPPINGADRESS, LOGIN, DESCRIPTION) 
	VALUES (7, 'P', 'Christophe', NULL, 'Exdgbtd', NULL, 'artisan3', 'Nulla sed quam posuere libero pharetra mattis id at tortor. Maecenas tincidunt vehicula lectus, non aliquet lacus interdum nec. Vivamus in nunc sem. Etiam tincidunt eros non sollicitudin tincidunt. Aenean imperdiet metus non rhoncus dictum. In hac habitasse platea dictumst. Curabitur commodo nisl eu sem placerat consectetur. Cras cursus purus ligula, ac pretium justo sodales id. Donec ut porta sapien. Cras id commodo justo. Donec consequat est lacus, ac feugiat urna mollis vel. Praesent metus tellus, commodo nec auctor et, congue eget urna. Nam finibus accumsan ante eu euismod. Nulla vel sodales lectus, ut mollis libero. Praesent nec risus tortor. Nam varius orci nisl, eu commodo lacus scelerisque sed.');
INSERT INTO ARTECOM.CLIENT (ID, PROFIL_TYPE, FIRSTNAME, LASTBILLINGADRESS, LASTNAME, LASTSHIPPINGADRESS, LOGIN, DESCRIPTION) 
	VALUES (8, 'P', 'Arthur', NULL, 'Api', NULL, 'artisan4', 'Suspendisse vel gravida turpis, at mattis ligula. Vestibulum vel pretium ante. Quisque facilisis consequat nulla, et pellentesque magna tempor vel. Donec laoreet hendrerit mi mattis auctor. Nam porta eget leo sed rhoncus. Aliquam fringilla ligula sed porttitor efficitur. Phasellus quis mollis ipsum, vitae scelerisque justo. Nam tristique tortor sit amet metus gravida, sit amet dapibus turpis efficitur. Proin ullamcorper a elit sit amet fringilla. Pellentesque in efficitur tellus, a rutrum eros. Praesent commodo, libero ac dignissim facilisis, arcu sem sodales velit, eu euismod enim est id velit. Ut scelerisque massa nec elit elementum, efficitur aliquam urna blandit.');

--sites
INSERT INTO ARTECOM.SITE (ID, DESCRIPTION, OPENING, "TYPE", LAT, LNG, CRAFTSMAN_ID, ADDRESS_ID) 
	VALUES (1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis at pulvinar neque. In blandit sem nec ligula cursus, quis molestie nunc sollicitudin. In eleifend quam nec diam volutpat suscipit. Mauris pellentesque mollis pharetra. Sed ex dui, viverra vel sapien at, eleifend consequat arcu. Vestibulum quis nibh eu elit lobortis finibus. Morbi in vulputate ex. In sit amet sodales ligula. Phasellus viverra magna mattis, ornare augue eu, bibendum leo. Nullam consequat ex in consectetur condimentum. Suspendisse potenti. ', NULL, 1, '45.1854387', '5.731330400000001', 1, 1);
INSERT INTO ARTECOM.SITE (ID, DESCRIPTION, OPENING, "TYPE", LAT, LNG, CRAFTSMAN_ID, ADDRESS_ID) 
	VALUES (2, 'Nullam tincidunt metus at mollis placerat. Nunc efficitur imperdiet turpis sit amet gravida. Morbi sagittis dui eu erat consectetur ultricies. Aliquam mattis felis eu turpis porta euismod. Donec lobortis nunc velit, nec gravida enim tempor sed. Ut eleifend at orci sit amet venenatis. Vivamus sed volutpat velit, id tincidunt velit. Fusce non sem rutrum, dignissim erat sed, varius tellus. Pellentesque tincidunt sem eget maximus egestas. Nulla volutpat tincidunt ante, in tincidunt lorem ornare vitae. ', NULL, 1, '45.1881713', '5.737055499999999', 1, 2);
INSERT INTO ARTECOM.SITE (ID, DESCRIPTION, OPENING, "TYPE", LAT, LNG, CRAFTSMAN_ID, ADDRESS_ID) 
	VALUES (3, 'Suspendisse vel gravida turpis, at mattis ligula. Vestibulum vel pretium ante. Quisque facilisis consequat nulla, et pellentesque magna tempor vel. Donec laoreet hendrerit mi mattis auctor. Nam porta eget leo sed rhoncus. Aliquam fringilla ligula sed porttitor efficitur. Phasellus quis mollis ipsum, vitae scelerisque justo. Nam tristique tortor sit amet metus gravida, sit amet dapibus turpis efficitur. Proin ullamcorper a elit sit amet fringilla. Pellentesque in efficitur tellus, a rutrum eros. Praesent commodo, libero ac dignissim facilisis, arcu sem sodales velit, eu euismod enim est id velit. Ut scelerisque massa nec elit elementum, efficitur aliquam urna blandit.', NULL, 1, '45.1868352', '5.723845799999999', 2, 4);
INSERT INTO ARTECOM.SITE (ID, DESCRIPTION, OPENING, "TYPE", LAT, LNG, CRAFTSMAN_ID, ADDRESS_ID) 
	VALUES (4, 'Quisque sem magna, hendrerit lacinia ultricies id, consequat sed libero. Pellentesque id vestibulum tortor. Nam consequat arcu et ligula scelerisque, in condimentum nisi semper. Vivamus ac bibendum justo. Vivamus efficitur mi ut massa varius fermentum. Nulla in sodales est, vitae commodo purus. Fusce eget vestibulum lacus. Curabitur eget sodales nisl, sed ultricies diam. Donec auctor maximus sapien, quis euismod velit imperdiet imperdiet. Sed in dictum felis, sit amet lobortis massa. Morbi sit amet convallis eros. Aenean sed purus dui. Curabitur fringilla massa id lectus tempus tristique. Praesent semper pulvinar aliquam. Integer elementum nulla sed posuere porttitor.', NULL, 1, '45.1860015', '5.7259214', 3, 3);


--products
INSERT INTO ARTECOM.PRODUCT (ID, AVAILABILITY, DESCRIPTION, "NAME", PRICE, QUANTITY, VERSION, WEIGHT, CRAFT_ID, CRAFTSMAN_ID, SITE_ID) 
	VALUES (1, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Product 1', 2.0, 20, 0, 0.3, 1, 5, 1);
INSERT INTO ARTECOM.PRODUCT (ID, AVAILABILITY, DESCRIPTION, "NAME", PRICE, QUANTITY, VERSION, WEIGHT, CRAFT_ID, CRAFTSMAN_ID, SITE_ID) 
	VALUES (2, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Product 2', 5.0, 200, 0, 0.2, 1, 6, 3);
INSERT INTO ARTECOM.PRODUCT (ID, AVAILABILITY, DESCRIPTION, "NAME", PRICE, QUANTITY, VERSION, WEIGHT, CRAFT_ID, CRAFTSMAN_ID, SITE_ID) 
	VALUES (3, 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Product 3', 5.0, 75, 0, 1.0, 5, 6, 3);
INSERT INTO ARTECOM.PRODUCT (ID, AVAILABILITY, DESCRIPTION, "NAME", PRICE, QUANTITY, VERSION, WEIGHT, CRAFT_ID, CRAFTSMAN_ID, SITE_ID) 
	VALUES (4, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Product 4', 45.0, 45, 0, 3.0, 2, 7, 4);
INSERT INTO ARTECOM.PRODUCT (ID, AVAILABILITY, DESCRIPTION, "NAME", PRICE, QUANTITY, VERSION, WEIGHT, CRAFT_ID, CRAFTSMAN_ID, SITE_ID) 
	VALUES (5, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Product 5', 100.0, 50, 0, 0.8, 2, 6, 3);
INSERT INTO ARTECOM.PRODUCT (ID, AVAILABILITY, DESCRIPTION, "NAME", PRICE, QUANTITY, VERSION, WEIGHT, CRAFT_ID, CRAFTSMAN_ID, SITE_ID) 
	VALUES (6, 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Product 6', 20.0, 70, 0, 0.2, 3, 7, 4);
INSERT INTO ARTECOM.PRODUCT (ID, AVAILABILITY, DESCRIPTION, "NAME", PRICE, QUANTITY, VERSION, WEIGHT, CRAFT_ID, CRAFTSMAN_ID, SITE_ID) 
	VALUES (7, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Product 7', 5.0, 70, 0, 0.2, 3, 5, 2);
INSERT INTO ARTECOM.PRODUCT (ID, AVAILABILITY, DESCRIPTION, "NAME", PRICE, QUANTITY, VERSION, WEIGHT, CRAFT_ID, CRAFTSMAN_ID, SITE_ID) 
	VALUES (8, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Product 8', 9.99, 70, 0, 0.45, 3, 7, 4);


--client_address
INSERT INTO ARTECOM.CLIENT_ADDRESS (CLIENT_ID, ADDRESS_ID) 
	VALUES (1, 5);
INSERT INTO ARTECOM.CLIENT_ADDRESS (CLIENT_ID, ADDRESS_ID) 
	VALUES (1, 6);
INSERT INTO ARTECOM.CLIENT_ADDRESS (CLIENT_ID, ADDRESS_ID) 
	VALUES (2, 7);
INSERT INTO ARTECOM.CLIENT_ADDRESS (CLIENT_ID, ADDRESS_ID) 
	VALUES (3, 8);
