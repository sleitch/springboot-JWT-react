import React, { useEffect, useState } from 'react';
import userService from '../services/userService';


const Profile = () => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchUserProfile = async () => {
            try {
                const response = await userService.getProfile();
                setUser(response.data);
            } catch (err) {
                console.error('authService.getUserProfile not impl -Error fetching user profile:', err);
                setError('Failed to fetch user profile');
            } finally {
                setLoading(false);
            }
        };

        fetchUserProfile();
    }, []);

    const handleUpdate = async (event) => {
        event.preventDefault();
        // Implement update logic here
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    return (
        <div>
            <h2>User Profile</h2>
            {user && (
                <form onSubmit={handleUpdate}>
                    <div>
                        <label>Username:</label>
                        <input type="text" value={user.username} readOnly />
                    </div>
                    <div>
                        <label>Email:</label>
                        <input type="email" value={user.email} readOnly />
                    </div>

                     <div>
                        <label>ID:</label>
                        <input type="email" value={user.id} readOnly />
                    </div>
                     <div>
                        <label>Display Name:</label>
                        <input type="email" value={user.displayName} readOnly />
                    </div>
                    {/* Add more fields as necessary */}
                    <button type="submit">Update Profile</button>
                </form>
            )}
        </div>
    );
};

export default Profile;