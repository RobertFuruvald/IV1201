--
-- PostgreSQL database dump
--

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: availability; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.availability (
    availability_id integer NOT NULL,
    person_id integer,
    from_date date,
    to_date date
);


ALTER TABLE public.availability OWNER TO postgres;

--
-- Name: availability_availability_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.availability ALTER COLUMN availability_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.availability_availability_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: competence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.competence (
    competence_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.competence OWNER TO postgres;

--
-- Name: competence_competence_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.competence ALTER COLUMN competence_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.competence_competence_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: competence_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.competence_profile (
    competence_profile_id integer NOT NULL,
    person_id integer,
    competence_id integer,
    years_of_experience numeric(4,2)
);


ALTER TABLE public.competence_profile OWNER TO postgres;

--
-- Name: competence_profile_competence_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.competence_profile ALTER COLUMN competence_profile_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.competence_profile_competence_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    person_id integer NOT NULL,
    name character varying(255),
    surname character varying(255),
    pnr character varying(255),
    email character varying(255),
    password character varying(255),
    role_id integer,
    username character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person ALTER COLUMN person_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.person_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.role ALTER COLUMN role_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

--
-- Data for Name: competence; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.competence (competence_id, name) FROM stdin;
1	ticket sales
2	lotteries
3	roller coaster operation
\.

--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (role_id, name) FROM stdin;
1	recruiter
2	applicant
\.



--
-- Name: availability availability_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.availability
    ADD CONSTRAINT availability_pkey PRIMARY KEY (availability_id);


--
-- Name: competence competence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.competence
    ADD CONSTRAINT competence_pkey PRIMARY KEY (competence_id);


--
-- Name: competence_profile competence_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.competence_profile
    ADD CONSTRAINT competence_profile_pkey PRIMARY KEY (competence_profile_id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (person_id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- Name: availability availability_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.availability
    ADD CONSTRAINT availability_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(person_id);


--
-- Name: competence_profile competence_profile_competence_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.competence_profile
    ADD CONSTRAINT competence_profile_competence_id_fkey FOREIGN KEY (competence_id) REFERENCES public.competence(competence_id);


--
-- Name: competence_profile competence_profile_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.competence_profile
    ADD CONSTRAINT competence_profile_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(person_id);


--
-- Name: person person_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(role_id);

--
-- Name: application; Type: TABLE; Schema: public; Owner: postgres
--
CREATE TABLE public.application (
    application_id SERIAL PRIMARY KEY,
    status varchar(255) default 'Unhandled',
    person_id INTEGER NOT NULL
);
--
-- Name: application person_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
ALTER TABLE ONLY public.application
    ADD CONSTRAINT application_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(person_id);


--
-- PostgreSQL database dump complete
--

