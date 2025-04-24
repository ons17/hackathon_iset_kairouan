// pages/member/UpdateMember.tsx
import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { TextField, Button, Box, Typography } from '@mui/material';
import axios from 'axios';

const UpdateMember: React.FC = () => {
  const { id } = useParams<{ id: string }>(); // Récupère l'ID du membre depuis l'URL
  const [member, setMember] = useState<any | null>(null); // Utilisez le type approprié pour votre membre

  const navigate = useNavigate();

  useEffect(() => {
    // Récupérer les informations du membre depuis l'API
    axios.get(`http://localhost:9090/api/adherents/${id}`)
    .then((response) => {
        setMember(response.data);
      })
      .catch((error) => {
        console.error('Erreur lors de la récupération des données du membre :', error);
      });
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (member) {
      setMember({
        ...member,
        [e.target.name]: e.target.value,
      });
    }
  };

  const handleSubmit = () => {
    if (member) {
      axios
        .put(`http://localhost:9090/api/adherents/${member.id}`, member)
        .then(() => {
          navigate('/list-member'); // Rediriger vers la liste des membres après la mise à jour
        })
        .catch((error) => {
          console.error('Erreur lors de la mise à jour du membre :', error);
        });
    }
  };

  return (
    <Box m={4}>
      <Typography variant="h4" gutterBottom>
        Modifier le Membre
      </Typography>
      {member ? (
        <form>
          <TextField
            label="Nom"
            name="lastName"
            value={member.lastName}
            onChange={handleChange}
            fullWidth
            margin="normal"
          />
          <TextField
            label="Prénom"
            name="firstName"
            value={member.firstName}
            onChange={handleChange}
            fullWidth
            margin="normal"
          />
          <TextField
            label="Email"
            name="email"
            value={member.email}
            onChange={handleChange}
            fullWidth
            margin="normal"
          />
          <TextField
            label="Téléphone"
            name="phone"
            value={member.phone}
            onChange={handleChange}
            fullWidth
            margin="normal"
          />
          <Button
            variant="contained"
            color="primary"
            onClick={handleSubmit}
            fullWidth
            style={{ marginTop: '20px' }}
          >
            Mettre à jour
          </Button>
        </form>
      ) : (
        <Typography variant="body1" color="error">
          Chargement des données...
        </Typography>
      )}
    </Box>
  );
};

export default UpdateMember;
