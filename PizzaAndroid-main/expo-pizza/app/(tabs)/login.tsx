import {
  Alert,
  Image,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from 'react-native'

import React, { useState } from 'react'
import ParallaxScrollView from '@/components/ParallaxScrollView'
import useCategories from '@/hooks/categories/useCategories'
import { ThemedView } from '@/components/ThemedView'
import { ThemedText } from '@/components/ThemedText'
import CategoryCard from '@/components/category/CategoryCard'
import { ICategory } from '@/interfaces/category'
import useCreateUser from '@/hooks/account/useCreateUser'
export default function LoginScreen() {
  const [firstName, onChangeFirstName] = React.useState('')
  const [lastName, onChangeLastName] = React.useState('')
  const [email, onChangeEmail] = React.useState('')
  const [password, onChangePassword] = React.useState('')
  const [image, setImage] = useState<File | null>(null)
  const createUserMutation = useCreateUser()

  const handleCreateUser = async () => {
    try {
      await createUserMutation.mutateAsync({
        firstName,
        lastName,
        email,
        password,
        image,
      })
      Alert.alert('User created successfully!')
    } catch (error) {
      console.log(error.message)
      Alert.alert('Error creating user', error)
    }
  }

  return (
    <ParallaxScrollView
      headerBackgroundColor={{ light: '#D0D0D0', dark: '#353636' }}
      headerImage={
        <Image
          resizeMode="cover"
          source={{
            uri: 'https://www.1stformationsblog.co.uk/wp-content/uploads/2022/09/Shutterstock_1079243414.jpg',
          }}
          style={styles.screenLogo}
        />
      }>
      <ThemedView style={styles.container}>
        <ThemedView style={styles.headerContainer}>
          <ThemedText style={styles.title}>Реєстрація</ThemedText>
        </ThemedView>

        <ThemedText style={styles.label}>Ім'я</ThemedText>
        <TextInput
          style={styles.input}
          placeholder={'Ім’я'}
          onChangeText={onChangeFirstName}
          value={firstName}
        />
        <ThemedText style={styles.label}>Прізвище</ThemedText>
        <TextInput
          style={styles.input}
          placeholder={'Прізвище'}
          onChangeText={onChangeLastName}
          value={lastName}
        />
        <ThemedText style={styles.label}>Електронна пошта</ThemedText>
        <TextInput
          style={styles.input}
          placeholder={'Електронна пошта'}
          onChangeText={onChangeEmail}
          value={email}
        />
        <ThemedText style={styles.label}>Пароль</ThemedText>
        <TextInput
          secureTextEntry={true}
          style={styles.input}
          placeholder={'Пароль'}
          onChangeText={onChangePassword}
          value={password}
        />
        <ThemedText style={styles.label}>Фото</ThemedText>

        <TouchableOpacity onPress={handleCreateUser} style={styles.button}>
          <ThemedText style={styles.buttonText}>Зареєструватися</ThemedText>
        </TouchableOpacity>
      </ThemedView>
    </ParallaxScrollView>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'transparent',
  },

  title: {
    fontSize: 24,
    fontWeight: 'bold',
  },

  label: {
    width: '100%',
    fontSize: 14,
    fontWeight: 'bold',
  },

  input: {
    backgroundColor: 'rgba(255,255,255,0.5)',
    width: '100%',
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
  },
  screenLogo: {
    height: '100%',
    width: '100%',
  },

  headerContainer: {
    backgroundColor: '#f3ff00',
    marginBottom: 20,
    paddingVertical: 15,
    paddingHorizontal: 30,
    transform: [{ rotate: '-5deg' }],
  },

  buttonText: {
    color: 'black',
    fontSize: 18,
    fontWeight: 'bold',
  },

  button: {
    backgroundColor: 'white',
    padding: 10,
    borderRadius: 5,
    marginTop: 10,
  },
})
