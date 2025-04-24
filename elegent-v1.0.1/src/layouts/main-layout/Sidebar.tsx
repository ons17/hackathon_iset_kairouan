import { List, ListItem, ListItemIcon, ListItemText, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import IconifyIcon from 'components/base/IconifyIcon';

const Sidebar = (): ReactElement => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Clear authentication data
    localStorage.removeItem('authToken'); // Example: Remove token
    sessionStorage.clear(); // Clear session storage if needed

    // Redirect to login page
    navigate('/authentication/login');
  };

  return (
    <List>
      {/* Other sidebar items */}
      <ListItem
        button
        onClick={handleLogout}
        sx={{
          color: 'error.main',
          '&:hover': {
            backgroundColor: 'error.light',
          },
        }}
      >
        <ListItemIcon>
          <IconifyIcon icon="ri:logout-circle-line" color="error.main" />
        </ListItemIcon>
        <ListItemText primary="Logout" />
      </ListItem>
    </List>
  );
};

export default Sidebar;