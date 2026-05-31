CREATE TABLE Sali (
                      nr_sala NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      etaj NUMBER(3) NOT NULL,
                      tip_sala VARCHAR2(50) NOT NULL
);

CREATE TABLE Servicii_Medicale (
                        id_serviciu NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        nume_serviciu VARCHAR2(100) NOT NULL,
                        pret NUMBER(8, 2) NOT NULL,
                        durata_minute NUMBER(3) NOT NULL
);

CREATE TABLE Medicamente (
                        id_medicament NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        nume VARCHAR2(100) NOT NULL,
                        dozaj NUMBER(4) NOT NULL,
                        substanta_activa VARCHAR2(100) NOT NULL
);

CREATE TABLE Pacienti (
                        id_pacient NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        cnp VARCHAR2(13) UNIQUE NOT NULL,
                        nume VARCHAR2(50) NOT NULL,
                        prenume VARCHAR2(50) NOT NULL,
                        nr_telefon VARCHAR2(15),
                        grupa_sanguina VARCHAR2(5),
                        parola VARCHAR2(100) NOT NULL
);

CREATE TABLE Medici (
                        id_medic NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        cnp VARCHAR2(13) UNIQUE NOT NULL,
                        nume VARCHAR2(50) NOT NULL,
                        prenume VARCHAR2(50) NOT NULL,
                        nr_telefon VARCHAR2(15),
                        salariu NUMBER(8, 2),
                        tura VARCHAR2(20),
                        data_angajarii DATE,
                        specializare VARCHAR2(50) NOT NULL,
                        cost_consultatie NUMBER(8, 2) NOT NULL,
                        cod_parafa VARCHAR2(20) UNIQUE NOT NULL,
                        parola VARCHAR2(100) NOT NULL
);

CREATE TABLE Asistenti (
                           id_asistent NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           cnp VARCHAR2(13) UNIQUE NOT NULL,
                           nume VARCHAR2(50) NOT NULL,
                           prenume VARCHAR2(50) NOT NULL,
                           nr_telefon VARCHAR2(15),
                           salariu NUMBER(8, 2),
                           tura VARCHAR2(20),
                           data_angajarii DATE,
                           grad_profesional VARCHAR2(50),
                           poate_recolta_probe NUMBER(1) DEFAULT 0
);

CREATE TABLE Retete (
                        id_reteta NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        id_medic NUMBER NOT NULL,
                        id_pacient NUMBER NOT NULL,
                        data_emiterii DATE NOT NULL,
                        CONSTRAINT fk_reteta_medic FOREIGN KEY (id_medic) REFERENCES Medici(id_medic),
                        CONSTRAINT fk_reteta_pacient FOREIGN KEY (id_pacient) REFERENCES Pacienti(id_pacient)
);

CREATE TABLE Medicament_Reteta (
                        id_reteta NUMBER NOT NULL,
                        id_medicament NUMBER NOT NULL,
                        PRIMARY KEY (id_reteta, id_medicament),
                        CONSTRAINT fk_mr_reteta FOREIGN KEY (id_reteta) REFERENCES Retete(id_reteta) ON DELETE CASCADE,
                        CONSTRAINT fk_mr_medicament FOREIGN KEY (id_medicament) REFERENCES Medicamente(id_medicament) ON DELETE CASCADE
);

CREATE TABLE Programari (
                            id_programare NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            id_pacient NUMBER NOT NULL,
                            id_medic NUMBER NOT NULL,
                            id_asistent NUMBER,
                            id_serviciu NUMBER NOT NULL,
                            nr_sala NUMBER NOT NULL,
                            data_si_ora TIMESTAMP NOT NULL,
                            status VARCHAR2(30) DEFAULT 'In asteptare',
                            CONSTRAINT fk_prog_pacient FOREIGN KEY (id_pacient) REFERENCES Pacienti(id_pacient),
                            CONSTRAINT fk_prog_medic FOREIGN KEY (id_medic) REFERENCES Medici(id_medic),
                            CONSTRAINT fk_prog_asistent FOREIGN KEY (id_asistent) REFERENCES Asistenti(id_asistent),
                            CONSTRAINT fk_prog_serviciu FOREIGN KEY (id_serviciu) REFERENCES Servicii_Medicale(id_serviciu),
                            CONSTRAINT fk_prog_sala FOREIGN KEY (nr_sala) REFERENCES Sali(nr_sala)
);

CREATE TABLE Facturi (
                         id_factura NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         id_programare NUMBER UNIQUE NOT NULL,
                         pret NUMBER(8, 2) NOT NULL,
                         data_emiterii DATE NOT NULL,
                         status_plata VARCHAR2(30) DEFAULT 'In asteptare',
                         CONSTRAINT fk_factura_programare FOREIGN KEY (id_programare) REFERENCES Programari(id_programare)
);