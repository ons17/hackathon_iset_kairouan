import { useEffect, useState } from 'react';
import { Typography, Stack, Button } from '@mui/material';

const Home = () => {
  const [userType, setUserType] = useState<string | null>(null);
  const [adherent, setAdherent] = useState<any>(null);

  useEffect(() => {
    const storedUserType = localStorage.getItem('userType');
    setUserType(storedUserType);

    if (storedUserType === 'adherent') {
      const storedAdherent = JSON.parse(localStorage.getItem('adherent') || '{}');
      setAdherent(storedAdherent);
    }
  }, []);

  const handleLogout = () => {
    localStorage.clear(); 
    window.location.reload(); 
  };

  return (
    <Stack spacing={3} alignItems="center" justifyContent="center" height="100vh">
      {userType === 'admin' && (
        <>
          <Typography variant="h4">Welcome, Admin!</Typography>
          <Button variant="contained" onClick={handleLogout}>
            Logout
          </Button>
        </>
      )}
      {userType === 'adherent' && adherent && (
        <>
          <Typography variant="h4">Welcome, {adherent.firstName}!</Typography>
          <Typography variant="body1">Email: {adherent.email}</Typography>
          <Button variant="contained" onClick={handleLogout}>
            Logout
          </Button>
        </>
      )}
      {!userType && (
        <Typography variant="h6" color="error">
          Please log in to access your account.
        </Typography>
      )}
    </Stack>
  );
};

export default Home;