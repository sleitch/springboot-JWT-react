import React, { useEffect, useState } from 'react';
import userService from '../services/userService';
import {
  Container,
  Box,
  Typography,
  TextField,
  Button,
  Paper,
  CircularProgress,
  Alert,
  Grid
} from '@mui/material';

const Profile = () => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [displayName, setDisplayName] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const response = await userService.getProfile();
        setUser(response.data);
        setDisplayName(response.data.displayName || '');
        setFirstName(response.data.firstName || '');
        setLastName(response.data.lastName || '');
        setEmail(response.data.email || '');
      } catch (err) {
        setError('Failed to fetch user profile');
      } finally {
        setLoading(false);
      }
    };

    fetchUserProfile();
  }, []);

  const handleUpdate = async (event) => {
    event.preventDefault();
    setError(null);
    setSuccess(false);
    try {
      await userService.updateProfile({
        displayName,
        firstName,
        lastName,
        email
      });
      setSuccess(true);
    } catch (err) {
      setError('Failed to update profile');
    }
  };

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', mt: 8 }}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return (
      <Container maxWidth="sm" sx={{ mt: 8 }}>
        <Alert severity="error">{error}</Alert>
      </Container>
    );
  }

  return (
    <Container maxWidth="sm" sx={{ mt: 8 }}>
      <Paper elevation={3} sx={{ p: 4 }}>
        <Typography variant="h4" gutterBottom>
          User Profile
        </Typography>
        {success && (
          <Alert severity="success" sx={{ mb: 2 }}>
            Profile updated successfully!
          </Alert>
        )}
        <Box component="form" onSubmit={handleUpdate} noValidate>
          <TextField
            margin="normal"
            fullWidth
            label="Username"
            value={user.username}
            InputProps={{ readOnly: true }}
            disabled
          />
          <TextField
            margin="normal"
            fullWidth
            label="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <TextField
            margin="normal"
            fullWidth
            label="Display Name"
            value={displayName}
            onChange={(e) => setDisplayName(e.target.value)}
          />
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                margin="normal"
                fullWidth
                label="First Name"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                margin="normal"
                fullWidth
                label="Last Name"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            sx={{ mt: 3 }}
            fullWidth
          >
            Update Profile
          </Button>
        </Box>
      </Paper>
    </Container>
  );
};

export default Profile;