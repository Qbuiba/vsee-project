import requests

# Define the GitHub API endpoint and the organization
api_url = "https://api.github.com"
org = "SeleniumHQ"

# Fetch the list of repositories for the organization
repos_url = f"{api_url}/orgs/{org}/repos"
response = requests.get(repos_url)
repos = response.json()

# Initialize variables
total_open_issues = 0
sorted_repos = []
most_watched_repo = None
max_watchers = 0

# Process each repository
for repo in repos:
    # Update the total number of open issues
    total_open_issues += repo['open_issues_count']
    
    # Check for the repository with the most watchers
    if repo['watchers_count'] > max_watchers:
        max_watchers = repo['watchers_count']
        most_watched_repo = repo
    
    # Add repository to the list for sorting
    sorted_repos.append(repo)

# Sort the repositories by date updated in descending order
sorted_repos.sort(key=lambda x: x['updated_at'], reverse=True)

# Output the results
print(f"Total open issues across all repositories: {total_open_issues}")
print("\nRepositories sorted by date updated (descending order):")
for repo in sorted_repos:
    print(f"{repo['name']} - Last updated at: {repo['updated_at']}")

if most_watched_repo:
    print(f"\nRepository with the most watchers: {most_watched_repo['name']} with {most_watched_repo['watchers_count']} watchers")

