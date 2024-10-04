# Import necessary libraries
from sklearn.neighbors import NearestNeighbors
from sklearn.preprocessing import StandardScaler, MinMaxScaler
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

# Load the video game sales dataset
data_file = 'D:/Workspace/Programming/_Projects/Python/ML Video Games/input/vgsales.csv'
games_data = pd.read_csv(data_file)

# Display the number of records and the first 5 rows of the dataset
print(f"No. of records: {games_data.shape[0]}")
games_data.head(5)

# Create a filtered dataset with selected columns and check information
filtered_games_data = games_data[['Name', 'Platform', 'Genre', 'Critic_Score', 'User_Score', 'Rating']].copy()
filtered_games_data.info()

# Check for missing values in the filtered dataset
filtered_games_data.isna().sum().sort_values(ascending=False)

# Drop rows with missing values in specific columns and reset the index
filtered_games_data.dropna(subset=['Name', 'Genre', 'Rating'], axis=0, inplace=True)
filtered_games_data = filtered_games_data.reset_index(drop=True)

# Check for missing values in specific columns after cleaning
filtered_games_data[['Name', 'Genre', 'Rating']].isna().sum()

# Define features for visualization
feats = filtered_games_data[['Genre', 'Platform', 'Rating']].columns

# Plot the distribution of video game genres
plt.figure(figsize=(13, 6))
sns.countplot(data=filtered_games_data, y='Genre', hue='Genre', palette='viridis', legend=False)
plt.ylabel('Genre')
plt.xlabel('Frequency')
plt.title("Distribution of Video Game Genres")
plt.show()

# Plot the distribution of video game platforms
plt.figure(figsize=(13, 6))
sns.countplot(data=filtered_games_data, y='Platform', hue='Platform', palette='Set3', legend=False)
plt.ylabel('Platform')
plt.xlabel('Frequency')
plt.title("Distribution of Video Game Platforms")
plt.show()

# Plot the distribution of video game ratings
plt.figure(figsize=(10, 6))
sns.countplot(data=filtered_games_data, y='Rating', hue='Rating', palette='pastel', legend=False)
plt.ylabel('Rating')
plt.xlabel('Frequency')
plt.title("Distribution of Video Game Ratings")
plt.show()

# Handle 'tbd' values in the 'User_Score' column and convert to float
filtered_games_data['User_Score'] = np.where(filtered_games_data['User_Score'] == 'tbd', np.nan, filtered_games_data['User_Score']).astype(float)

# Group by genre and calculate average scores
groupby_genre = filtered_games_data[['Genre', 'Critic_Score', 'User_Score']].groupby('Genre', as_index=False)
avg_scores = groupby_genre.agg(Ave_Critic_Score=('Critic_Score', 'mean'), Ave_User_Score=('User_Score', 'mean'))

# Merge average scores back to the dataset
filtered_games_data = filtered_games_data.merge(avg_scores, on='Genre')
filtered_games_data

# Impute missing critic and user scores with average scores
filtered_games_data['Critic_Score_Imputed'] = np.where(filtered_games_data['Critic_Score'].isna(), filtered_games_data['Ave_Critic_Score'], filtered_games_data['Critic_Score'])
filtered_games_data['User_Score_Imputed'] = np.where(filtered_games_data['User_Score'].isna(),  filtered_games_data['Ave_User_Score'], filtered_games_data['User_Score'])
filtered_games_data[['Critic_Score', 'Critic_Score_Imputed', 'User_Score', 'User_Score_Imputed']].describe()

# Create the final dataset by dropping unnecessary columns
final_games_data = filtered_games_data.drop(columns=['User_Score', 'Critic_Score', 'Ave_Critic_Score', 'Ave_User_Score'], axis=1)
final_games_data = final_games_data.reset_index(drop=True)

# Rename columns for clarity
final_games_data = final_games_data.rename(columns={'Critic_Score_Imputed':'Critic_Score', 'User_Score_Imputed':'User_Score'})
final_games_data.info()


# Visualize the distribution of critic scores
plt.figure(figsize=(10, 6))
sns.histplot(data=final_games_data, x='Critic_Score', bins=20, kde=True, color='skyblue')
plt.xlabel('Critic Score')
plt.ylabel('Frequency')
plt.title("Distribution of Critic Scores")
plt.show()

# Visualize the distribution of user scores
plt.figure(figsize=(10, 6))
sns.histplot(data=final_games_data, x='User_Score', bins=20, kde=True, color='salmon')
plt.xlabel('User Score')
plt.ylabel('Frequency')
plt.title("Distribution of User Scores")
plt.show()

# Create a scatter plot of user scores vs. critic scores
plt.figure(figsize=(12, 8))
scatter_plot = sns.scatterplot(x='User_Score', y='Critic_Score', data=final_games_data, hue='Genre', alpha=0.7)
sns.regplot(x='User_Score', y='Critic_Score', data=final_games_data, scatter=False, color='black', line_kws={"linewidth": 2})
plt.xlabel('User Score')
plt.ylabel('Critic Score')
plt.title("Scatter Plot of User Scores vs. Critic Scores")
plt.legend(bbox_to_anchor=(1.02, 0.5), loc='center left')
plt.tight_layout()
plt.show()

# Identify categorical features in the final dataset
category = [name for name in final_games_data.columns if final_games_data[name].dtype == 'O']
category = category[1:]

# Display the number of categorical features and their names
print(f'There are {len(category)} categorical features:\n')
print(", ".join(category))

# Convert categorical features to dummy variables
games_data_dummy = pd.get_dummies(data=final_games_data, columns=category)

# Display information about the dataset with dummy variables
games_data_dummy.info()

# Extract features for scaling
feats = games_data_dummy.drop(columns=['Name'], axis=1)

# Apply MinMax scaling to critic and user scores
data_scaler = MinMaxScaler()
games_data_dummy['Critic_Score'] = data_scaler.fit_transform(games_data_dummy[['Critic_Score']])
games_data_dummy['User_Score'] = data_scaler.fit_transform(games_data_dummy[['User_Score']])

# Apply standard scaling to the remaining features
standard_scaler = StandardScaler()
scaled_feats = standard_scaler.fit_transform(feats)
scaled_feats = pd.DataFrame(scaled_feats, columns=feats.columns)

# Display the first 5 rows of the scaled features
scaled_feats.head(5)

# Build a k-NN model using cosine similarity
knn_model = NearestNeighbors(n_neighbors=11, metric='cosine', algorithm='brute').fit(scaled_feats)
print(knn_model)

# Find nearest neighbors for each game in the dataset
game_distances, game_indices = knn_model.kneighbors(scaled_feats)

# Display the list of indexes and distances for the first 5 games
print("List of indexes and distances for the first 5 games:\n")
print(game_indices[:5], "\n")
print(game_distances[:5])

# Extract unique game names for vectorization
game_names = games_data_dummy['Name'].drop_duplicates()
game_names = game_names.reset_index(drop=True)

# Vectorize game titles using TF-IDF
text_vectorizer = TfidfVectorizer(use_idf=True).fit(game_names)
title_vector = text_vectorizer.transform(game_names)

# Display the list of game title vectors for the first 5 games
print("List of game title vectors for the first 5 games:\n")
print(pd.DataFrame(title_vector.toarray()).head(5))

# Function to find the best match for a selected game
def MatchMaker(selected_game):
    query_vector = text_vectorizer.transform([selected_game])
    similarity_scores = cosine_similarity(query_vector, title_vector)
    best_match_index = similarity_scores.argmax()
    best_match_game = game_names[best_match_index]
    return best_match_game

# Function to provide game recommendations based on user input
def GiveMe(selected_game, selected_platform='Any'):
    default_platform = 'Any'
    input_name = selected_game.lower()
    video_game_idx = final_games_data.query("Name.str.lower() == @input_name").index

    if video_game_idx.empty:
        best_match_game = MatchMaker(input_name)
        print(f"\n'{selected_game}' doesn't exist in the records.")
        print(f"You may want to try '{best_match_game.capitalize()}', which is the closest match to the input.")
    else:
        if selected_platform != default_platform:
            selected_platform = selected_platform.lower()
            if final_games_data.loc[video_game_idx[0], 'Platform'].lower() != selected_platform:
                print(f"\n'{selected_game}' doesn't exist on the specified platform '{selected_platform}'.")
                print("However, here are some recommendations based on the title of the game:\n")
                video_game_idx = final_games_data.query("Name.str.lower() == @input_name").index
        if selected_platform == default_platform:
            merged_distance_idx = pd.DataFrame()
            for idx in video_game_idx:
                game_distance_idx = pd.concat([pd.DataFrame(game_indices[idx][1:]), pd.DataFrame(game_distances[idx][1:])], axis=1)
                merged_distance_idx = pd.concat([merged_distance_idx, game_distance_idx])
            merged_distance_idx.columns = ['Index', 'Distance']
            merged_distance_idx = merged_distance_idx.reset_index(drop=True)
            merged_distance_idx = merged_distance_idx.sort_values(by='Distance', ascending=True)
            games_list = final_games_data.iloc[merged_distance_idx['Index']]
            games_list = games_list.drop_duplicates(subset=['Name'], keep='first')
            games_list = games_list.head(10)
            suggested_distances = np.array(merged_distance_idx['Distance'].head(10))
        else:
            suggested_idx = game_indices[video_game_idx[0]][1:]
            games_list = final_games_data.iloc[suggested_idx]
            suggested_distances = np.array(game_distances[video_game_idx[0]][1:])
        print(f"Top 10 Recommended Video Games for '{selected_game}' [platform:{selected_platform}]")
        games_list = games_list.reset_index(drop=True)
        suggested_games_list = pd.concat([games_list, pd.DataFrame(suggested_distances, columns=['Similarity_Distance'])], axis=1)
        print(suggested_games_list.to_string(index=False))
        print("\nPlease provide your feedback for each recommended video game.")
        print("1: Liked, 2: Neutral, 3: Disliked\n")
        feedback_list = []
        for i, (_, row) in enumerate(suggested_games_list.iterrows(), 1):
            valid_input = False
            while not valid_input:
                try:
                    feedback = int(input(f"'{row['Name']}': "))
                    if feedback in [1, 2, 3]:
                        valid_input = True
                    else:
                        print("Invalid input! Please enter 1 for Liked, 2 for Neutral, or 3 for Disliked.")
                except ValueError:
                    print("Invalid input! Please enter a valid number (1, 2, or 3).")
            feedback_list.append(feedback)
        suggested_games_list['Feedback'] = feedback_list
        feedback_mapping = {1: 'liked', 2: 'neutral', 3: 'disliked'}
        suggested_games_list['Feedback_Type'] = suggested_games_list['Feedback'].map(feedback_mapping)
        correct_feedback = (suggested_games_list['Feedback'] == 1).sum()
        total_recommendations = len(suggested_games_list)
        user_satisfaction = correct_feedback / total_recommendations
        print(f"\nSatisfaction Rate: {user_satisfaction:.2%}")
        print(suggested_games_list[['Name', 'Feedback_Type']].to_string(index=False))

# User interaction loop for input and recommendation
while True:
    selected_game = input("\nEnter the name of the video game (type 'q' to quit): ").lower()
    if selected_game.lower() == 'q':
        break
    selected_platform = input("Enter the platform (type 'any' for any platform): ").lower()
    GiveMe(selected_game, selected_platform)
