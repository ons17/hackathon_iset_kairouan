import React, { useEffect, useState } from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  Typography,
  Box
} from '@mui/material';
import axios, { AxiosResponse } from 'axios';
import { Link } from 'react-router-dom';

interface Adherent {
  id: number;
  lastName: string;
  firstName: string;
  email: string;
  phone: string;
}

const ListMember: React.FC = () => {
  const [adherents, setAdherents] = useState<Adherent[]>([]);

  useEffect(() => {
    axios
      .get<Adherent[]>('http://localhost:9090/api/adherents')
      .then((response: AxiosResponse<Adherent[]>) => {
        setAdherents(response.data);
      })
      .catch((error) => {
        console.error('Erreur lors du chargement des adhérents :', error);
      });
  }, []);

  return (
    <Box m={4}>
      <Typography variant="h4" gutterBottom>
        Liste des Adhérents
      </Typography>
      <TableContainer component={Paper}>
        <Table aria-label="Liste des adhérents">
          <TableHead>
            <TableRow>
              <TableCell>Nom</TableCell>
              <TableCell>Prénom</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Téléphone</TableCell>
              <TableCell align="center">Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {adherents.map((adherent) => (
              <TableRow key={adherent.id}>
                <TableCell>{adherent.lastName}</TableCell>
                <TableCell>{adherent.firstName}</TableCell>
                <TableCell>{adherent.email}</TableCell>
                <TableCell>{adherent.phone}</TableCell>
                <TableCell align="center">
                  <Button
                    variant="contained"
                    color="primary"
                    component={Link}
                    to={`/list-member/update/${adherent.id}`}
                    >
                    Update
                  </Button>
                </TableCell>
              </TableRow>
            ))}
            {adherents.length === 0 && (
              <TableRow>
                <TableCell colSpan={5} align="center">
                  Aucun adhérent trouvé.
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default ListMember;
