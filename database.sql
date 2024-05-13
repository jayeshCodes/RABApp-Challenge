--
-- PostgreSQL database dump
--

-- Dumped from database version 15.6 (Homebrew)
-- Dumped by pg_dump version 15.6 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: renumber_ids(); Type: FUNCTION; Schema: public; Owner: jayeshgajbhar
--

CREATE FUNCTION public.renumber_ids() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF TG_OP = 'DELETE' THEN
        UPDATE movement
        SET id = id - 1
        WHERE id > OLD.id;
    END IF;
    RETURN NULL;
END;
$$;


ALTER FUNCTION public.renumber_ids() OWNER TO jayeshgajbhar;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_user; Type: TABLE; Schema: public; Owner: jayeshgajbhar
--

CREATE TABLE public.app_user (
    id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255)
);


ALTER TABLE public.app_user OWNER TO jayeshgajbhar;

--
-- Name: app_user_id_seq; Type: SEQUENCE; Schema: public; Owner: jayeshgajbhar
--

CREATE SEQUENCE public.app_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_id_seq OWNER TO jayeshgajbhar;

--
-- Name: app_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jayeshgajbhar
--

ALTER SEQUENCE public.app_user_id_seq OWNED BY public.app_user.id;


--
-- Name: company_id_seq; Type: SEQUENCE; Schema: public; Owner: jayeshgajbhar
--

CREATE SEQUENCE public.company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_id_seq OWNER TO jayeshgajbhar;

--
-- Name: companies; Type: TABLE; Schema: public; Owner: jayeshgajbhar
--

CREATE TABLE public.companies (
    company_id bigint DEFAULT nextval('public.company_id_seq'::regclass) NOT NULL,
    company_name character varying(100)
);


ALTER TABLE public.companies OWNER TO jayeshgajbhar;

--
-- Name: farm; Type: TABLE; Schema: public; Owner: jayeshgajbhar
--

CREATE TABLE public.farm (
    premiseid character varying(7) NOT NULL,
    total_animal integer,
    farm_company character varying(255),
    farm_latitude double precision,
    farm_longitude double precision
);


ALTER TABLE public.farm OWNER TO jayeshgajbhar;

--
-- Name: movement; Type: TABLE; Schema: public; Owner: jayeshgajbhar
--

CREATE TABLE public.movement (
    id integer NOT NULL,
    account_company character varying(255),
    new_movementreason character varying(255),
    new_species character varying(255),
    new_originaddress character varying(255),
    new_origincity character varying(255),
    new_originname character varying(255),
    new_originpostalcode character varying(255),
    new_originpremid character varying(7),
    new_originstate character varying(255),
    new_destinationaddress character varying(255),
    new_destinationcity character varying(255),
    new_destinationname character varying(255),
    new_destinationpostalcode character varying(255),
    new_destinationpremid character varying(7),
    new_destinationstate character varying(255),
    origin_lat double precision,
    origin_lon double precision,
    destination_lat double precision,
    destination_long double precision,
    new_numitemsmoved integer,
    new_shipmentsstartdate timestamp(6) without time zone
);


ALTER TABLE public.movement OWNER TO jayeshgajbhar;

--
-- Name: movement_id_seq; Type: SEQUENCE; Schema: public; Owner: jayeshgajbhar
--

CREATE SEQUENCE public.movement_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movement_id_seq OWNER TO jayeshgajbhar;

--
-- Name: movement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jayeshgajbhar
--

ALTER SEQUENCE public.movement_id_seq OWNED BY public.movement.id;


--
-- Name: app_user id; Type: DEFAULT; Schema: public; Owner: jayeshgajbhar
--

ALTER TABLE ONLY public.app_user ALTER COLUMN id SET DEFAULT nextval('public.app_user_id_seq'::regclass);


--
-- Name: movement id; Type: DEFAULT; Schema: public; Owner: jayeshgajbhar
--

ALTER TABLE ONLY public.movement ALTER COLUMN id SET DEFAULT nextval('public.movement_id_seq'::regclass);


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: jayeshgajbhar
--

COPY public.app_user (id, email, name, password) FROM stdin;
31	user@fake.com	User	$2a$10$GJpzCGizkDmPo6uRYq2qe.BUhXM36.3H5.Tx3jHvUXojJmAQyihom
\.


--
-- Data for Name: companies; Type: TABLE DATA; Schema: public; Owner: jayeshgajbhar
--

COPY public.companies (company_id, company_name) FROM stdin;
1	Mypigcompany
2	Pigs and Co
3	Beefs
\.


--
-- Data for Name: farm; Type: TABLE DATA; Schema: public; Owner: jayeshgajbhar
--

COPY public.farm (premiseid, total_animal, farm_company, farm_latitude, farm_longitude) FROM stdin;
464KBIF	1000	Mypigcompany	46.07227	-93.91527
477EDVX	1000	Mypigcompany	45.88508	-94.49498
225ZGAJ	1000	Mypigcompany	41.90538	-97.56184
325UDIW	1000	Mypigcompany	41.0829	-94.3104
733DTME	1000	Mypigcompany	43.07102	-99.01941
360FDCZ	1000	Mypigcompany	44.33265	-96.68334
853OBOV	1000	Mypigcompany	43.80099	-98.72475
220FAPG	1000	Mypigcompany	46.22264	-93.76849
582PHBR	1000	Mypigcompany	42.97002	-88.94877
730AOYL	1000	Mypigcompany	42.391528	-86.477042
778EYUX	1000	Mypigcompany	46.38174	-92.14026
180UCBY	1000	Mypigcompany	42.10786	-101.59227
671HMJV	1000	Mypigcompany	39.67177	-90.09549
554DGRT	1000	Mypigcompany	45.53324	-94.59197
453YIAE	1000	Mypigcompany	44.67619	-89.01749
715ELOH	1000	Mypigcompany	41.87143	-103.33981
027RDXY	1000	Mypigcompany	45.03986	-84.37985
376DQUF	1000	Mypigcompany	42.04312	-94.73784
611ULSK	1000	Mypigcompany	46.36619	-93.63721
088WKFT	1000	Mypigcompany	41.99145	-103.34611
\.


--
-- Data for Name: movement; Type: TABLE DATA; Schema: public; Owner: jayeshgajbhar
--

COPY public.movement (id, account_company, new_movementreason, new_species, new_originaddress, new_origincity, new_originname, new_originpostalcode, new_originpremid, new_originstate, new_destinationaddress, new_destinationcity, new_destinationname, new_destinationpostalcode, new_destinationpremid, new_destinationstate, origin_lat, origin_lon, destination_lat, destination_long, new_numitemsmoved, new_shipmentsstartdate) FROM stdin;
1	Mypigcompany	FINISH TO FINISH	Swine	163, fake Street	Carthage	my_farm_name_B	62321	376DQUF	IL	743, fake Street	Sheffield	my_farm_name_L	50475	778EYUX	IA	42.04312	-94.73784	46.38174	-92.14026	1000	2011-04-18 00:00:00
2	Mypigcompany	SOW TO FINISH	Swine	677, fake Street	Edison	my_farm_name_C	68936	088WKFT	NE	733, fake Street	Sac City	my_farm_name_R	50583	464KBIF	IA	41.99145	-103.34611	46.07227	-93.91527	1000	2011-04-18 00:00:00
3	Mypigcompany	SOW TO NURSERY	Swine	154, fake Street	Bethany	my_farm_name_J	64424	225ZGAJ	MO	028, fake Street	Battle Creek	my_farm_name_K	51006	477EDVX	IA	41.90538	-97.56184	45.88508	-94.49498	1000	2011-04-18 00:00:00
4	Mypigcompany	WTF TO FINISH	Swine	707, fake Street	Clay Center	my_farm_name_B	68933	180UCBY	NE	112, fake Street	Havelock	my_farm_name_M	50574	611ULSK	IA	42.10786	-101.59227	46.36619	-93.63721	1000	2011-04-18 00:00:00
5	Mypigcompany	WTF TO FINISH	Swine	217, fake Street	Albion	my_farm_name_E	46701	582PHBR	IN	012, fake Street	Wawaka	my_farm_name_S	46794	027RDXY	IN	42.97002	-88.94877	45.03986	-84.37985	1000	2011-04-18 00:00:00
6	Mypigcompany	FINISH TO FINISH	Swine	308, fake Street	Hampton	my_farm_name_I	50441	360FDCZ	IA	834, fake Street	Fonda	my_farm_name_S	50540	220FAPG	IA	44.33265	-96.68334	46.22264	-93.76849	1000	2011-04-18 00:00:00
7	Mypigcompany	WTF TO FINISH	Swine	026, fake Street	Shelby	my_farm_name_C	51570	733DTME	IA	058, fake Street	Ute	my_farm_name_O	51060	554DGRT	IA	43.07102	-99.01941	45.53324	-94.59197	1000	2011-04-18 00:00:00
8	Mypigcompany	SOW TO NURSERY	Swine	813, fake Street	Edison	my_farm_name_H	68936	715ELOH	NE	550, fake Street	Edison	my_farm_name_R	68936	853OBOV	NE	41.87143	-103.33981	43.80099	-98.72475	1000	2011-04-18 00:00:00
9	Mypigcompany	SOW TO WTF	Swine	582, fake Street	Pittsfield	my_farm_name_B	62363	325UDIW	IL	846, fake Street	Altona	my_farm_name_Q	61414	453YIAE	IL	41.0829	-94.3104	44.67619	-89.01749	1000	2011-04-18 00:00:00
10	Mypigcompany	SOW TO FINISH	Swine	501, fake Street	Leopold	my_farm_name_J	47551	671HMJV	IN	613, fake Street	Flat Rock	my_farm_name_T	62427	730AOYL	IL	39.67177	-90.09549	42.391528	-86.477042	1000	2011-04-18 00:00:00
\.


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jayeshgajbhar
--

SELECT pg_catalog.setval('public.app_user_id_seq', 31, true);


--
-- Name: company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jayeshgajbhar
--

SELECT pg_catalog.setval('public.company_id_seq', 3, true);


--
-- Name: movement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jayeshgajbhar
--

SELECT pg_catalog.setval('public.movement_id_seq', 82, true);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: jayeshgajbhar
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: companies companies_pkey; Type: CONSTRAINT; Schema: public; Owner: jayeshgajbhar
--

ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pkey PRIMARY KEY (company_id);


--
-- Name: farm farm_pkey; Type: CONSTRAINT; Schema: public; Owner: jayeshgajbhar
--

ALTER TABLE ONLY public.farm
    ADD CONSTRAINT farm_pkey PRIMARY KEY (premiseid);


--
-- Name: movement movement_pkey; Type: CONSTRAINT; Schema: public; Owner: jayeshgajbhar
--

ALTER TABLE ONLY public.movement
    ADD CONSTRAINT movement_pkey PRIMARY KEY (id);


--
-- Name: movement movement_renumber_ids_trigger; Type: TRIGGER; Schema: public; Owner: jayeshgajbhar
--

CREATE TRIGGER movement_renumber_ids_trigger AFTER DELETE ON public.movement FOR EACH ROW EXECUTE FUNCTION public.renumber_ids();


--
-- Name: movement movement_new_destinationpremid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: jayeshgajbhar
--

ALTER TABLE ONLY public.movement
    ADD CONSTRAINT movement_new_destinationpremid_fkey FOREIGN KEY (new_destinationpremid) REFERENCES public.farm(premiseid);


--
-- Name: movement movement_new_originpremid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: jayeshgajbhar
--

ALTER TABLE ONLY public.movement
    ADD CONSTRAINT movement_new_originpremid_fkey FOREIGN KEY (new_originpremid) REFERENCES public.farm(premiseid);


--
-- PostgreSQL database dump complete
--

