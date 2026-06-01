INSERT INTO Sali (etaj, tip_sala) VALUES (1, 'Consultatii Generale');
INSERT INTO Sali (etaj, tip_sala) VALUES (1, 'Cardiologie');
INSERT INTO Sali (etaj, tip_sala) VALUES (2, 'Chirurgie Minora');
INSERT INTO Sali (etaj, tip_sala) VALUES (2, 'Recoltare Analize');
INSERT INTO Sali (etaj, tip_sala) VALUES (3, 'Radiologie');

INSERT INTO Servicii_Medicale (nume_serviciu, pret, durata_minute) VALUES ('Consultatie Generala', 150.00, 30);
INSERT INTO Servicii_Medicale (nume_serviciu, pret, durata_minute) VALUES ('Consultatie Cardiologie', 250.00, 45);
INSERT INTO Servicii_Medicale (nume_serviciu, pret, durata_minute) VALUES ('Ecografie Abdominala', 200.00, 30);
INSERT INTO Servicii_Medicale (nume_serviciu, pret, durata_minute) VALUES ('Recoltare Sange', 50.00, 15);
INSERT INTO Servicii_Medicale (nume_serviciu, pret, durata_minute) VALUES ('Interpretare Analize', 100.00, 15);

INSERT INTO Medicamente (nume, dozaj, substanta_activa) VALUES ('Paracetamol', 500, 'Paracetamolum');
INSERT INTO Medicamente (nume, dozaj, substanta_activa) VALUES ('Nurofen Forte', 400, 'Ibuprofenum');
INSERT INTO Medicamente (nume, dozaj, substanta_activa) VALUES ('Augmentin', 1000, 'Amoxicillinum');
INSERT INTO Medicamente (nume, dozaj, substanta_activa) VALUES ('Aspirina Saraca', 100, 'Acidum acetylsalicylicum');
INSERT INTO Medicamente (nume, dozaj, substanta_activa) VALUES ('Concor', 5, 'Bisoprololum');

COMMIT;