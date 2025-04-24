import { useEffect, useState } from 'react';
import axios from 'axios';
import { Typography, Stack, CircularProgress } from '@mui/material';

const AdminProfile = () => {
  const [admin, setAdmin] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAdmin = async () => {
      try {
        const adminId = localStorage.getItem('adminId'); // Assuming admin ID is stored in localStorage
        const response = await axios.get(`http://localhost:9090/api/admin/GetAdmin/${adminId}`);
        setAdmin(response.data);
      } catch (err) {
        setError('Failed to fetch admin details');
      } finally {
        setLoading(false);
      }
    };

    fetchAdmin();
  }, []);

  if (loading) return <CircularProgress />;
  if (error) return <Typography color="error">{error}</Typography>;

  return (
    <Stack spacing={2}>
      <Typography variant="h4">Admin Profile</Typography>
      <Typography variant="body1">Name: {admin?.name}</Typography>
      <Typography variant="body1">Email: {admin?.email}</Typography>
    </Stack>
  );
};

export default AdminProfile;