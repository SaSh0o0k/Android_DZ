import React from 'react'
import { Stack } from 'expo-router'

const CategoryLayout = () => {
  return (
    <Stack>
      <Stack.Screen
        name={'[id]'}
        options={{
          title: 'Категорія',
          headerShown: false,
        }}
      />
    </Stack>
  )
}

export default CategoryLayout
