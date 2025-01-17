PGDMP     5                
    |            ubicaciones    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    91411    ubicaciones    DATABASE        CREATE DATABASE ubicaciones WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Mexico.1252';
    DROP DATABASE ubicaciones;
                postgres    false            �            1259    91420    persona    TABLE     Z  CREATE TABLE public.persona (
    id integer NOT NULL,
    nombres character varying(100) NOT NULL,
    primer_apellido character varying(100) NOT NULL,
    segundo_apellido character varying(100) NOT NULL,
    edad integer NOT NULL,
    fecha_nacimiento date NOT NULL,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.persona;
       public         heap    postgres    false            �            1259    91419    persona_id_seq    SEQUENCE     �   CREATE SEQUENCE public.persona_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.persona_id_seq;
       public          postgres    false    217            	           0    0    persona_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.persona_id_seq OWNED BY public.persona.id;
          public          postgres    false    216            �            1259    91413    ubicaciones    TABLE     �   CREATE TABLE public.ubicaciones (
    id integer NOT NULL,
    nombre character varying(100),
    latitud numeric(9,6),
    longitud numeric(9,6)
);
    DROP TABLE public.ubicaciones;
       public         heap    postgres    false            �            1259    91412    ubicaciones_id_seq    SEQUENCE     �   CREATE SEQUENCE public.ubicaciones_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.ubicaciones_id_seq;
       public          postgres    false    215            
           0    0    ubicaciones_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.ubicaciones_id_seq OWNED BY public.ubicaciones.id;
          public          postgres    false    214            k           2604    91423 
   persona id    DEFAULT     h   ALTER TABLE ONLY public.persona ALTER COLUMN id SET DEFAULT nextval('public.persona_id_seq'::regclass);
 9   ALTER TABLE public.persona ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            j           2604    91416    ubicaciones id    DEFAULT     p   ALTER TABLE ONLY public.ubicaciones ALTER COLUMN id SET DEFAULT nextval('public.ubicaciones_id_seq'::regclass);
 =   ALTER TABLE public.ubicaciones ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215                      0    91420    persona 
   TABLE DATA           y   COPY public.persona (id, nombres, primer_apellido, segundo_apellido, edad, fecha_nacimiento, fecha_creacion) FROM stdin;
    public          postgres    false    217   7                  0    91413    ubicaciones 
   TABLE DATA           D   COPY public.ubicaciones (id, nombre, latitud, longitud) FROM stdin;
    public          postgres    false    215   �                  0    0    persona_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.persona_id_seq', 6, true);
          public          postgres    false    216                       0    0    ubicaciones_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.ubicaciones_id_seq', 23, true);
          public          postgres    false    214            p           2606    91426    persona persona_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            postgres    false    217            n           2606    91418    ubicaciones ubicaciones_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.ubicaciones
    ADD CONSTRAINT ubicaciones_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.ubicaciones DROP CONSTRAINT ubicaciones_pkey;
       public            postgres    false    215               �   x���;�0��>H�G�69U+T(j)����c �R������}���5O�A"01�9� $�1;�S֘ż)������Y�g��.-��,4��U����Ś<I�T`�<:ܟ�����9Rx"��`��:�ih�����G�zH����l�@�X���]�Q�          5   x�34�L�45�3 N#c(�����.	KF�$qI�0�!���� fI#i     