# Android Lab Tasks - README

## 📱 Overview
This repository contains two comprehensive Android development lab tasks focusing on core Android concepts including UI design, authentication, database management, fragments, and navigation.

## 📋 Task 1: Student Portal App with Login

### Objective
Create a student portal application with secure login functionality and dashboard features.

### Key Features

#### Part 1: Main Activity (Login)
- **UI Components**: Two EditText fields (Username & Password) + Login Button
- **Authentication**: Hardcoded user validation with role-based access
- **User Database**:
  ```java
  String[] usernames = {"student1", "student2", "admin"};
  String[] passwords = {"pass123", "pass456", "admin123"};
  String[] Roles = {"student", "student", "systemAdmin"};
  ```
- **Login Logic**: Validates credentials and navigates to appropriate activity with extras

#### Part 2: Student Dashboard Activity
1. **Contact Professor**: Opens email client with pre-filled template
2. **Emergency Alert**: Creates emergency notification with student details
3. **Campus Navigation**: Opens Google Maps with predefined campus locations
4. **Schedule Meeting**: Admin-only feature (Toast notification)

### Technical Requirements
- Explicit Intents for activity navigation
- Intent extras for data passing
- Notifications API
- Google Maps integration
- Role-based UI visibility

## 📋 Task 2: Student Information App with Navigation

### Objective
Build a student information application demonstrating Android architecture patterns, database management, and fragment navigation.

### Technical Requirements

#### 1. SharedPreferences with Singleton Pattern
- Create `SharedPrefManager` singleton class
- Store 6 profile fields:
  - Full Name
  - Student ID
  - Email
  - Phone Number
  - Department
  - Year of Study

#### 2. SQLite Database
- Database: `StudentNotes.db`
- Table: `notes` with columns:
  - `id` (Primary Key, Auto-increment)
  - `title` (Text)
  - `content` (Text)
- CRUD operations implementation

#### 3. Fragment Architecture
- **ProfileFragment**: User profile form with validation
- **NotesFragment**: Note creation and management interface
- Bottom navigation bar with visual feedback
- Fragment transactions without back stack

#### 4. Animations
- Fade-in animation for fragment titles
- Scale animation for Save buttons
- Visual feedback for active/inactive navigation buttons

### Fragment Specifications

#### ProfileFragment
- Load existing profile data on open
- Validate required fields (Name, Student ID)
- Save to SharedPreferences
- Clear functionality
- Button animations

#### NotesFragment
- Input validation for title and content
- SQLite database integration
- Automatic display of latest note
- Note count tracking
- Field clearing after save

## 🛠 Development Setup

### Prerequisites
- Android Studio (latest version)
- Android SDK (API level 28+)
- Java 8 or higher
- Physical device or emulator with Google Play Services

### Project Structure
```
Task1_StudentPortal/
├── app/
│   ├── src/main/java/
│   │   ├── LoginActivity.java
│   │   ├── StudentDashboardActivity.java
│   │   └── ...
│   └── src/main/res/
│       ├── layout/
│       ├── values/
│       └── ...

Task2_StudentInformation/
├── app/
│   ├── src/main/java/
│   │   ├── MainActivity.java
│   │   ├── SharedPrefManager.java
│   │   ├── DatabaseHelper.java
│   │   ├── ProfileFragment.java
│   │   ├── NotesFragment.java
│   │   └── ...
│   └── src/main/res/
│       ├── layout/
│       ├── anim/
│       ├── values/
│       └── ...
```

### Implementation Notes

#### For Task 1:
- Use `Toast.makeText()` for error messages
- Implement `NotificationCompat.Builder` for emergency alerts
- Use `Uri.parse()` for Google Maps coordinates
- Manage activity lifecycle appropriately

#### For Task 2:
- Follow singleton pattern for SharedPreferences
- Implement proper SQLiteOpenHelper subclass
- Use FragmentManager for fragment transactions
- Apply animations using XML anim resources

## 📦 Submission Requirements

### Both Tasks Require:
1. Complete Android Studio project
2. All source code files
3. Resource files (layouts, drawables, animations)
4. Exported as `.zip` file from Android Studio
5. File size in KB mentioned in submission

### Grading Criteria:
- **Task 1**: Functional login, role-based navigation, all dashboard features
- **Task 2**: Proper singleton implementation, database operations, fragment navigation, animations

## 🔧 Testing Guidelines

### Test Cases for Task 1:
1. Valid login with different roles
2. Invalid login attempts
3. Email intent launching
4. Notification creation
5. Google Maps navigation
6. Admin-only feature visibility

### Test Cases for Task 2:
1. Profile data persistence
2. Required field validation
3. Note CRUD operations
4. Fragment navigation
5. Animation execution
6. UI state management

## 📚 Learning Outcomes
Upon completing these tasks, you will have demonstrated proficiency in:
- Android UI design and event handling
- User authentication and role-based access
- Local data persistence (SharedPreferences, SQLite)
- Fragment architecture and navigation
- Intent-based communication
- Animation implementation
- Notification system

## ⚠️ Common Pitfalls to Avoid
1. **Memory leaks**: Unregister listeners in appropriate lifecycle methods
2. **UI thread blocking**: Perform database operations asynchronously
3. **Hardcoded values**: Use string resources for text
4. **Back stack management**: Configure fragment transactions properly
5. **Resource management**: Close database connections when not in use

## 🆘 Troubleshooting
- If maps don't open: Ensure device has Google Maps installed
- If notifications don't show: Check notification permissions
- If database operations fail: Verify table creation and column names
- If animations don't work: Check XML syntax and resource references
## name: Layan Buirat 1211439
